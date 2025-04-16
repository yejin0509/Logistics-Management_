package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.InventoryListElseService;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

public class SearchByElseHandler implements CommandHandler {
	private InventoryListElseService inventory_List_Else_Service = new InventoryListElseService();
	private InventoryListService2 inventory_List_Service2 = new InventoryListService2();
	String product_Type = null;
	String product_Name = null;
	String company = null;
	String location = null;
	int max_Price = 0;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("Handler executed: SearchByElseHandler");

		try {
			// 기존 데이터 초기화
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

			// 서비스 클래스에서 product_list에 넣은 값을 list형에 넣어줌
			List inventory_Data = inventory_List_Else_Service.get_Inventory_Product_List(product_Type, company,
					location);
			// 저장 영역에 맞는 값 저장

			// 상품 분류 가져오기
			List<String> product_Type2 = inventory_List_Service2.getDistinctProductType();
			req.setAttribute("product_Type", product_Type2);

			// 회사명 가져오기
			List<String> company2 = inventory_List_Service2.getDistinctCompany();
			req.setAttribute("company", company2);

			// 저장 위치 가져오기
			List<String> location2 = inventory_List_Service2.getDistinctLocation();
			req.setAttribute("location", location2);

			int countProduct = inventory_List_Service2.countProduct();
			req.setAttribute("countProduct", countProduct);

			req.setAttribute("inventory_Data", inventory_Data);

			System.out.println("product_Type = " + product_Type);
			System.out.println("location : " + location);
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
