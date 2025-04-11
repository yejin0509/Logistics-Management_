package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.InventoryListElseService;
import mvc.command.CommandHandler;

public class SearchByElseHandler implements CommandHandler {
	private InventoryListElseService inventory_List_Else_Service = new InventoryListElseService();
	String product_Type = null;
	String product_Name = null;
	String company = null;
	String location = null;
	int max_Price = 0;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("Handler executed: SearchByElseHandler");

		try {
			//기존 데이터 초기화
			req.setAttribute("inventory_Data", null);
			
			
			// 서비스에서 들어온 값을 String으로 저장
			String product_Type = req.getParameter("product_Type");
			String product_Name = req.getParameter("product_Name");
			String company = req.getParameter("company");
			String location = req.getParameter("location");
			String max_Amount = req.getParameter("max_Amount");
			int max_Price = 0;
			if (max_Amount != null && !max_Amount.isEmpty()) {
				max_Price = Integer.parseInt(max_Amount);
			}
			

			System.out.println("product_Type: " + product_Type);
			// 어디까지 작동했는지 확인하려고 넣은 코드

			// 서비스 클래스에서 product_list에 넣은 값을 list형에 넣어줌
			List inventory_Data = inventory_List_Else_Service.get_Inventory_Product_List(product_Type, product_Name,
					company, location, max_Price);
			// 저장 영역에 맞는 값 저장
			req.setAttribute("inventory_Data", inventory_Data);

			System.out.println("검색 결과 수: " + inventory_Data.size());
			// 예외 처리
		} catch (Exception e) {
			e.printStackTrace(); // 로그 확인 필수
		}

		// JSP 페이지 경로 반환
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/inventoryListPage.jsp");
		dispatcher.forward(req, res);
		return null; // forward 했기 때문에 반환은 null

	}
}
