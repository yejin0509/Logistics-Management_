package kanri.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DateFilteredOutBoundService;
import kanri.service.OutBoundPage;
import mvc.command.CommandHandler;

public class DateFilteredOutBoundHandler implements CommandHandler {

    private DateFilteredOutBoundService service = new DateFilteredOutBoundService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String out_Date = req.getParameter("out_Date");
        
        if (out_Date == null || out_Date.trim().isEmpty()) {
        	req.setAttribute("error", "주문 날짜를 선택해주세요.");		
        	return "/WEB-INF/view/customerScan2.jsp";
        }
        
        
        SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd");
        Date out_dateDate = date_Format.parse(out_Date);
    	


        //나중에 안되면 다른 것이랑 outDate파라미터 값 맞는지 확인 하기?
        
        // 기본값 처리: 날짜 선택 안 했을 때


        OutBoundPage outBoundPage = service.getFilteredList(out_dateDate);
        req.setAttribute("OutBoundPage", outBoundPage);
        return "/WEB-INF/view/customerScan2.jsp";						//경로도 확인 합시다  한페이지에서 사용 할 것 인지 아니면은 다른 페이지 만들 것 인지
    }
}

