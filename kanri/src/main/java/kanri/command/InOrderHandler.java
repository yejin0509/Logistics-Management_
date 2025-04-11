package kanri.command;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.model.InBound;
import kanri.service.InboundService;

@WebServlet("/InOrderHandler")
public class InOrderHandler extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] productIds = request.getParameterValues("productId");  // 또는 productName -> ID 매핑
	    String[] quantities = request.getParameterValues("quantity");
	    String[] prices = request.getParameterValues("productPrice");

	    List<InBound> inboundList = new ArrayList<>();

	    for (int i = 0; i < productIds.length; i++) {
	    	InBound ob = new InBound();

	    	//String managerId = (String) request.getSession().getAttribute("managerId");
	        ob.setClient_Id("111");  // 임의로 지정
	        ob.setProduct_Id(productIds[i]);
	        ob.setIn_Date(new java.sql.Date(System.currentTimeMillis()));
	        ob.setIn_Count(Integer.parseInt(quantities[i]));
	        ob.setPrice(Integer.parseInt(prices[i]));
	        inboundList.add(ob);
	    }

	    InboundService service = new InboundService();
	    //DB 발주 신청 시 성공여부 페이지 연결
	    try {
	        service.saveOutboundList(inboundList);
	        response.sendRedirect("inboundSuccess.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("inboundFail.jsp");
	    }
	}
	
	


}
