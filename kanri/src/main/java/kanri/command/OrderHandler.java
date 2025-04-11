package kanri.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.model.OutBound;
import kanri.service.OutboundService;

@WebServlet("/OrderHandler")
public class OrderHandler extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] productIds = request.getParameterValues("productId");  // 또는 productName -> ID 매핑
	    String[] quantities = request.getParameterValues("quantity");
	    String[] prices = request.getParameterValues("productPrice");

	    List<OutBound> outboundList = new ArrayList<>();

	    for (int i = 0; i < productIds.length; i++) {
	    	OutBound ob = new OutBound();

	    	//String clientId = (String) request.getSession().getAttribute("clientId");
	        ob.setClient_Id("111");  // 임의로 지정
	        ob.setProduct_Id(productIds[i]);
	        ob.setOut_Date(new java.sql.Date(System.currentTimeMillis()));
	        ob.setOut_Count(Integer.parseInt(quantities[i]));
	        ob.setPrice(Integer.parseInt(prices[i]));
	        outboundList.add(ob);
	    }

	    OutboundService service = new OutboundService();
	    //DB 발주 신청 시 성공여부 페이지 연결
	    try {
	        service.saveOutboundList(outboundList);
	        response.sendRedirect("outboundSuccess.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("outboundFail.jsp");
	    }
	}
	
	


}
