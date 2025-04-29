package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.InBoundPage;
import kanri.service.ListInBoundService;
import mvc.command.CommandHandler;

public class ListInBoundeHandler implements CommandHandler {

	private ListInBoundService listService = new ListInBoundService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		
		InBoundPage inBoundPage = listService.getInBoundPage(pageNo);
		req.setAttribute("InBoundPage", inBoundPage);
		return"WEB-INF/view/customerScan.jsp";
	}



}