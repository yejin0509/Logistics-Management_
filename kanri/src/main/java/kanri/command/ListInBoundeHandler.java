package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.InBoundPage;
import kanri.service.ListInBoundService;
import mvc.command.CommandHandler;

/**
 * 입고 목록(페이지네이션 포함) 요청 처리 핸들러
 * 入庫一覧の取得処理（ページネーション対応）にゅうこ いちらん・しょり
 */
public class ListInBoundeHandler implements CommandHandler {

	private ListInBoundService listService = new ListInBoundService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 페이지 번호 파라미터 가져오기
		// ページ番号のパラメーターを取得（ばんごう）
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;  // 기본값: 1페이지

		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);  // 문자열을 정수로 변환
		}

		// 입고 목록 페이징 데이터 가져오기
		// 入庫ページデータを取得（にゅうこ）
		InBoundPage inBoundPage = listService.getInBoundPage(pageNo);

		// JSP에 데이터 전달
		// JSPにデータを渡す（わたす）
		req.setAttribute("InBoundPage", inBoundPage);

		// 뷰 페이지 경로 반환 (오타 수정 필요: "WEB-INF/..." → "/WEB-INF/...")
		// 表示ページに移動（ひょうじ）
		return "/WEB-INF/view/customerScan.jsp";
	}
}
