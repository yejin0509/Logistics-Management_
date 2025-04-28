package kanri.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DateFilteredInBoundService;
import kanri.service.InBoundPage;
import mvc.command.CommandHandler;

public class DateFilteredInBoundHandler implements CommandHandler {

    private DateFilteredInBoundService service = new DateFilteredInBoundService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
        String in_Date = req.getParameter("in_Date");
        
        if (in_Date == null || in_Date.trim().isEmpty()) {
            req.setAttribute("error", "주문 날짜를 선택해주세요.");
            return "/WEB-INF/view/customerScan.jsp";
        }
        
        SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd");
        Date in_dateDate = date_Format.parse(in_Date);


        InBoundPage inBoundPage = service.getFilteredList(in_dateDate);
        req.setAttribute("InBoundPage", inBoundPage);
        return "/WEB-INF/view/customerScan.jsp";
    }
}
