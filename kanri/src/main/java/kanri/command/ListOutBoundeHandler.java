package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.ListOutBoundService;
import kanri.service.OutBoundPage;
import mvc.command.CommandHandler;

/**
 * 출고 목록(페이지네이션 포함) 요청 처리 핸들러
 * 出庫一覧の取得処理（ページネーション対応）しゅっこ いちらん・しょり
 */
public class ListOutBoundeHandler implements CommandHandler {

	private ListOutBoundService listService = new ListOutBoundService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 페이지 번호 파라미터 받기
		// ページ番号のパラメーターを取得（ばんごう）
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;  // 기본은 1페이지

		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);  // 문자열을 정수로 변환
		}

		// 출고 페이지 객체 생성
		// 出庫ページデータを取得（しゅっこ）
		OutBoundPage outBoundPage = listService.getOutBoundPage(pageNo);

		// JSP로 전달
		// JSPにデータを渡す（わたす）
		req.setAttribute("OutBoundPage", outBoundPage);

		// 출력할 JSP 경로 반환 (※ 슬래시 누락되어 있던 부분 수정)
		// 表示ページへ移動（ひょうじ）
		return "/WEB-INF/view/customerScan2.jsp";
	}
}
