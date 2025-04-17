package kanri.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.model.Client;
import kanri.model.OutBound;
import kanri.service.OutboundService;

@WebServlet("/OrderHandler")
public class OrderHandler extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] product_Ids = request.getParameterValues("productId");  // 또는 productName -> ID 매핑
	    String[] quantities = request.getParameterValues("quantity");
	    String[] prices = request.getParameterValues("productPrice");
    	Client client = (Client) request.getSession().getAttribute("authClient");
    	String clientId = client.getClient_Id();

	    List<OutBound> outbound_List = new ArrayList<>();

	    for (int i = 0; i < product_Ids.length; i++) {
	    	OutBound ob = new OutBound();

	    	ob.setClient_Id(clientId);  // 임의로 지정
	        ob.setProduct_Id(product_Ids[i]);
	        ob.setOut_Date(new java.sql.Date(System.currentTimeMillis()));
	        ob.setOut_Count(Integer.parseInt(quantities[i]));
	        ob.setPrice(Integer.parseInt(prices[i]));
	        outbound_List.add(ob);
	    }

	    OutboundService service = new OutboundService();
	    //DB 발주 신청 시 성공여부 페이지 연결
	    try {
	        service.saveOutboundList(outbound_List);
	        response.sendRedirect("boundSuccess.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("boundFail.jsp");
	    }
	}
	
	


}
