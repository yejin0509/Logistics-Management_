package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.ListOutBoundService;
import kanri.service.OutBoundPage;
import mvc.command.CommandHandler;

public class ListOutBoundeHandler implements CommandHandler {

	private ListOutBoundService listService = new ListOutBoundService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		
		OutBoundPage outBoundPage = listService.getOutBoundPage(pageNo);
		req.setAttribute("OutBoundPage", outBoundPage);
		return"WEB-INF/view/customerScan2.jsp";						//여기도 경로 확인
	}



}
