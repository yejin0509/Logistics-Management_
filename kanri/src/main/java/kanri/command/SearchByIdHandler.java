package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService;
import mvc.command.CommandHandler;
//아이디 검색 했을 때 발생하는 핸들러
public class SearchByIdHandler implements CommandHandler {
	//서비스 불러오기
	private InventoryListService inventory_List_Service = new InventoryListService();

		@Override
		public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		    //어디까지 작동했는지 확인하려고 넣은 코드
			System.out.println("Handler executed: SearchByIdHandler");

		    try {
		    	//서비스에서 들어온 값을 String으로 저장
		        String product_Id = req.getParameter("product_Id");
		        //어디까지 작동했는지 확인하려고 넣은 코드
		        System.out.println("Received product_Id: " + product_Id);
		        
		        //서비스 클래스에서 값을 못 불러왔을 떄 Exception
		        if (product_Id == null || product_Id.trim().isEmpty()) {
		        	//RunTimeException 받아와서 만든 거
		            throw new IdNotFoundException();
		        }

		        //서비스 클래스에서 product_list에 넣은 값을 list형에 넣어줌
		        List inventory_Data = inventory_List_Service.get_Inventory_Product_List(product_Id);
		        //저장 영역에 맞는 값 저장
		        req.setAttribute("search_Product_Id", product_Id);
		        req.setAttribute("inventory_Data", inventory_Data);

		        //예외 처리
		    } catch (NumberFormatException e) {
		        req.setAttribute("errorMessage", "상품 ID는 숫자여야 합니다.");
		        System.out.println("NumberFormatException: " + e.getMessage());
		    } catch (IdNotFoundException e) {
		        req.setAttribute("errorMessage", "해당 ID의 상품을 찾을 수 없습니다.");
		        System.out.println("IdNotFoundException: " + e.getMessage());
		    } catch (Exception e) {
		        req.setAttribute("errorMessage", "예상치 못한 오류가 발생했습니다.");
		        e.printStackTrace();  // 로그 확인 필수
		    }

		    //응답 view로 반환
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/inventoryListPage.jsp");
			dispatcher.forward(req, res);
			return null; // forward 했기 때문에 반환은 null
		}

}
