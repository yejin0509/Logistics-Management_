package kanri.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.ManagerJoinRequest;
import kanri.service.ManagerJoinService;
import kanri.service.DuplicateIdException;
import mvc.command.CommandHandler;

/**
 * 관리자 회원가입 처리 핸들러
 * 管理者登録処理ハンドラー（かんりしゃ・とうろく）
 */
public class ManagerJoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/managerJoinForm.jsp"; // 가입 폼 JSP 경로
	private ManagerJoinService joinService = new ManagerJoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// GET 방식: 가입 폼 보여주기
		// GETの場合：登録画面の表示（とうろく・ひょうじ）
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
		}
		// POST 방식: 폼 제출 처리
		// POSTの場合：登録処理（とうろく・しょり）
		else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		// 허용되지 않은 방식
		// 許可されないメソッド（きょか）
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	/**
	 * 회원가입 폼 제출 처리
	 * 登録フォーム送信処理（そうしん・しょり）
	 */
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		ManagerJoinRequest joinRequest = new ManagerJoinRequest();

		// 파라미터 설정
		// パラメーターの設定（せってい）
		joinRequest.setManager_Id(req.getParameter("manager_Id"));
		joinRequest.setPassword(req.getParameter("password"));
		joinRequest.setConfirmPassword(req.getParameter("confirmPassword"));
		joinRequest.setRegisterCode(req.getParameter("registerCode"));

		// 에러 담을 맵 생성
		// エラーマップを生成（せいせい）
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 유효성 검사
		// バリデーションチェック（けんさ）
		joinRequest.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;  // 에러 시 다시 폼으로
		}

		try {
			joinService.join(joinRequest);  // 가입 시도
			return "/managerJoinSuccess.jsp";  // 성공 시 페이지 이동
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);  // 중복 ID 에러 처리
			return FORM_VIEW;
		}
	}
}
