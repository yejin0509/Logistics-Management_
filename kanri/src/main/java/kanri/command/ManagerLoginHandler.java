package kanri.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ManagerDao;
import kanri.model.Manager;
import mvc.command.CommandHandler;

public class ManagerLoginHandler implements CommandHandler {

    // 로그인 폼 페이지 경로 (로그인 페이지)
    // ログインフォームのパス
    private static final String FORM_VIEW = "/managerLoginForm.jsp";
    private ManagerDao managerDao = new ManagerDao();

    // 클라이언트의 요청을 처리하는 메서드
    // クライアントからのリクエストを処理するメソッド
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 요청 방식이 GET일 경우, 로그인 폼을 반환
        // リクエストメソッドがGETの場合、ログインフォームを返す
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return FORM_VIEW;  // 로그인 폼 화면으로 이동
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);  // POST 방식으로 로그인 처리
        } else {
            // GET이나 POST가 아닌 다른 방식의 요청은 허용하지 않음
            // GETやPOST以外のリクエストメソッドは許可しない
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);  // 405 Method Not Allowed
            return null;
        }
    }

    // 로그인 폼에서 제출된 데이터 처리 (POST 방식)
    // ログインフォームから送信されたデータの処理 (POSTメソッド)
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        // 요청 파라미터에서 관리자 ID와 비밀번호 가져오기
        // リクエストパラメータから管理者IDとパスワードを取得
        String manager_Id = req.getParameter("manager_Id");
        String password = req.getParameter("password");

        // 에러 정보를 담을 Map (예: ID 또는 비밀번호 오류)
        // エラー情報を格納するMap（例：IDまたはパスワードのエラー）
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);  // 에러를 JSP 페이지에 전달

        // 관리자 ID가 없거나 비어 있는 경우
        // 管理者IDが無い、または空である場合
        if (manager_Id == null || manager_Id.trim().isEmpty()) {
            errors.put("manager_Id", Boolean.TRUE);
        }
        // 비밀번호가 없거나 비어 있는 경우
        // パスワードが無い、または空である場合
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", Boolean.TRUE);
        }

        // 에러가 있으면 로그인 폼을 다시 표시
        // エラーがある場合、再度ログインフォームを表示
        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }

        try (Connection conn = ConnectionProvider.getConnection()) {
            // 관리자 정보 가져오기 (DB에서 관리자 ID로 검색)
            // 管理者情報を取得する（DBから管理者IDで検索）
            Manager manager = managerDao.selectById(conn, manager_Id);
            // 관리자 정보가 없거나 비밀번호가 일치하지 않는 경우
            // 管理者情報が無い、またはパスワードが一致しない場合
            if (manager == null || !manager.getPassword().equals(password)) {
                errors.put("idOrPwNotMatch", Boolean.TRUE);  // ID 또는 비밀번호 불일치
                return FORM_VIEW;  // 로그인 폼을 다시 표시
            }

            // 로그인 성공 → 세션에 관리자 정보 저장
            // ログイン成功 → セッションに管理者情報を保存
            HttpSession session = req.getSession();
            session.setAttribute("authManager", manager);  // 세션에 로그인한 관리자 정보를 저장
            System.out.println("로그인 성공: 관리자 ID = " + manager.getManager_Id());  // 로그인 성공 메시지 출력
            // ログイン成功: 管理者ID = " + manager.getManager_Id()  // ログイン成功メッセージを表示

            // 로그인 성공 후 관리자 메뉴 페이지로 리디렉션
            // ログイン成功後、管理者メニュー画面にリダイレクト
            return "/managerMenu.jsp";

        } catch (SQLException e) {
            // SQL 오류 발생 시 예외 처리
            // SQLエラーが発生した場合、例外処理
            throw new RuntimeException(e);  // 예외를 런타임 예외로 감싸서 던짐
        }
    }
}
