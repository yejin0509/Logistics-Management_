package kanri.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

//아이디 검색 했을 때 발생하는 핸들러
public class SearchByNameHandler implements CommandHandler {
	// 서비스 불러오기
	private InventoryListService inventory_List_Service = new InventoryListService();
	private InventoryListService2 inventory_List_Service2 = new InventoryListService2();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getAttribute("alreadyForwarded") != null) {
			return null; // 이미 처리된 요청이라면 더 이상 진행하지 않음
		}
		req.setAttribute("alreadyForwarded", true);

		// 어디까지 작동했는지 확인하려고 넣은 코드
		System.out.println("Handler executed: SearchByNameHandler");

		try {
			// 서비스에서 들어온 값을 String으로 저장
			String product_Name = req.getParameter("product_Name");
			// 어디까지 작동했는지 확인하려고 넣은 코드
			System.out.println("Received product_Name: " + product_Name);

			// 서비스 클래스에서 값을 못 불러왔을 떄 Exception
			if (product_Name == null || product_Name.trim().isEmpty()) {
				// RunTimeException 받아와서 만든 거
				throw new IdNotFoundException();
			}

			// 상품 분류 가져오기
			List<String> product_Type2 = inventory_List_Service2.getDistinctProductType();
			req.setAttribute("product_Type", product_Type2);

			// 회사명 가져오기
			List<String> company2 = inventory_List_Service2.getDistinctCompany();
			req.setAttribute("company", company2);

			// 저장 위치 가져오기
			List<String> location2 = inventory_List_Service2.getDistinctLocation();
			req.setAttribute("location", location2);

			// 서비스 클래스에서 product_list에 넣은 값을 list형에 넣어줌
			List inventory_Data = inventory_List_Service.getInventoryProductListByName(product_Name);
			// 전체 개수 가져오기
			int countProduct = inventory_List_Service.countProduct();
			req.setAttribute("countProduct", countProduct);
			// 저장 영역에 맞는 값 저장
			req.setAttribute("search_Product_Name", product_Name);
			req.setAttribute("inventory_Data", inventory_Data);

			// 예외 처리
		} catch (Exception e) {
			req.setAttribute("errorMessage", "예상치 못한 오류가 발생했습니다.");
			e.printStackTrace(); // 로그 확인 필수
		}

		return "/WEB-INF/view/inventoryListPage.jsp";

	}

}
