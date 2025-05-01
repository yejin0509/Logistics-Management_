package kanri.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientDao;
import kanri.dao.ManagerDao;
import kanri.model.Client;
import mvc.command.CommandHandler;

public class ClientLoginHandler implements CommandHandler {

    // 보여줄 JSP 경로 // 表示するJSPのパス（ひょうじする JSP の パス）
    private static final String FORM_VIEW = "/clientLoginForm.jsp";
    private ClientDao clientDao = new ClientDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            // 로그인 폼 화면 보여주기 
        	// ログインフォーム画面を表示（がめんを ひょうじ）
            return FORM_VIEW;

        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            // 나중에 로그인 검증 추가할 수 있음 
        	// 今後ログイン検証を追加可能（こんご ログイン けんしょう を ついか かのう）
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }
    
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        String client_Id = req.getParameter("client_Id");
        String password = req.getParameter("password");

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        if (client_Id == null || client_Id.trim().isEmpty()) {
            errors.put("client_Id", Boolean.TRUE); 
            // 아이디 입력 안 했을 경우 
            // IDを入力していない場合（にゅうりょく していない ばあい）
        }
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", Boolean.TRUE); 
            // 비밀번호 입력 안 했을 경우 
            // パスワードを入力していない場合（にゅうりょく していない ばあい）
        }

        if (!errors.isEmpty()) {
            return FORM_VIEW; 
            // 오류 발생 시 로그인 화면으로 
            // エラーがある場合はログイン画面へ（がめん へ）
        }

        try (Connection conn = ConnectionProvider.getConnection()) {
            Client client = clientDao.selectById(conn, client_Id); 
            // DB에서 아이디로 정보 조회 
            // DBからIDで情報を取得（じょうほう を しゅとく）
            if (client == null || !client.getPassword().equals(password)) {
                errors.put("idOrPwNotMatch", Boolean.TRUE); 
                // 아이디 또는 비밀번호 불일치 
                // IDまたはパスワードが一致しない（いっち しない）
                return FORM_VIEW;
            }

            // 로그인 성공 → 세션에 저장 
            // ログイン成功 → セッションに保存（せいこう → ほぞん）
            HttpSession session = req.getSession();
            session.setAttribute("authClient", client);
            System.out.println("로그인 성공: 관리자 ID = " + client.getClient_Id()); 
            // 로그인 성공 후 이동할 경로 (수정 필요) 
            // ログイン後に移動するパス（いどう する パス、修正必要）
            return "/clientMenu.jsp";

        } catch (SQLException e) {
            throw new RuntimeException(e); 
            // 예외 발생 시 런타임 에러로 던짐 
            // 例外発生時にランタイムエラーをスロー（れいがい はっせいじ）
        }
    }
}
