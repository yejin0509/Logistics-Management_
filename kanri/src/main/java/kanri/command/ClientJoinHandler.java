package kanri.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DuplicateIdException;
import kanri.service.ClientJoinRequest;
import kanri.service.ClientJoinService;
import mvc.command.CommandHandler;

public class ClientJoinHandler implements CommandHandler {

	// 회원가입 폼 뷰의 경로를 지정합니다.
	// 会員登録フォームのビューのパスを指定します。
	private static final String FORM_VIEW = "/clientJoinForm.jsp";

	// 실제 회원가입 처리를 담당하는 서비스 객체 생성
	// 実際の会員登録処理を担当するサービスオブジェクトを生成
	private ClientJoinService joinService = new ClientJoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// GET 요청일 경우, 폼을 보여줍니다.
		// GETリクエストの場合、フォームを表示します。
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
		// POST 요청일 경우, 제출 처리
		// POSTリクエストの場合、フォームの送信処理を行います。
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			// 허용되지 않은 HTTP 메서드인 경우 405 에러 반환
			// 許可されていないHTTPメソッドの場合、405エラーを返します。
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 클라이언트 요청 데이터를 담을 객체 생성
		// クライアントのリクエストデータを格納するオブジェクトを生成
		ClientJoinRequest joinRequest = new ClientJoinRequest();

		// 요청 파라미터를 받아서 joinRequest에 설정
		// リクエストパラメータを取得してjoinRequestに設定します。
		joinRequest.setClient_Id(req.getParameter("client_Id"));
		joinRequest.setPassword(req.getParameter("password"));
		joinRequest.setConfirmPassword(req.getParameter("confirmPassword"));
		joinRequest.setCompany(req.getParameter("company"));
		joinRequest.setAddress(req.getParameter("address"));
		joinRequest.setTosAgree(req.getParameter("tosAgree") != null);  // 약관 동의 체크 여부
		joinRequest.setPrivacyAgree(req.getParameter("privacyAgree") != null); // 개인정보 동의 체크 여부

		// 연락처 번호를 3부분으로 나눠 받아서 조합합니다.
		// 電話番号を3つの部分で受け取り、結合します。
		String part1 = req.getParameter("c_Number1");
		String part2 = req.getParameter("c_Number2");
		String part3 = req.getParameter("c_Number3");

		// 에러 메시지를 담을 Map 생성
		// エラーメッセージを格納するMapを生成
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors); // 에러를 request 영역에 저장
		// エラーをrequestスコープに保存

		// 연락처 항목 중 하나라도 비어 있으면 에러 처리
		// 電話番号の項目のいずれかが空の場合、エラー処理
		if (part1 == null || part1.trim().isEmpty()
				|| part2 == null || part2.trim().isEmpty()
				|| part3 == null || part3.trim().isEmpty()) {
			errors.put("c_Number", Boolean.TRUE);
		} else {
			// 전체 전화번호 조합 후 저장
			// 電話番号を結合して保存
			String fullNumber = part1 + "-" + part2 + "-" + part3;
			joinRequest.setC_Number(fullNumber);
		}

		// 입력 값 검증 실행
		// 入力値のバリデーションを実行
		joinRequest.validate(errors);

		// 에러가 존재하면 폼 다시 보여주기
		// エラーが存在する場合、フォームを再表示
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			// 회원가입 서비스 호출
			// 会員登録サービスを呼び出す
			joinService.join(joinRequest);
			// 성공 시 성공 페이지로 이동
			// 成功時、成功ページへ遷移
			return "/clientJoinSuccess.jsp";
		} catch (DuplicateIdException e) {
			// 아이디 중복 예외 처리
			// IDの重複による例外処理
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
