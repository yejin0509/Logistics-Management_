package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;
//아무것도 값이 들어가지 않은 채로 join된 query를 불러오기 위해 만든 핸들러
//처음 InventoryList페이지를 로딩하거나 초기화 버튼을 눌렀을 때 작동
public class NothingIncludeHandler implements CommandHandler {
	//InventoryListService 복사해서 2가 된 겁니다
	//큰 틀에서만 따온 거라 살짝 잉여 코드들이 존재
	private InventoryListService2 inventory_List_Service2 = new InventoryListService2();

		@Override
		public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		    System.out.println("Handler executed: NothingIncludeHandler");

		    try {
		    	//인자 없이 받아온 서비스를 저장
		        List inventory_Data = inventory_List_Service2.get_Inventory_Product_List();
		        req.setAttribute("inventory_Data", inventory_Data);

		        //상품 분류 가져오기
		        List<String> product_Type = inventory_List_Service2.getDistinctProductType();
		        req.setAttribute("product_Type", product_Type);

		        //회사명 가져오기
		        List<String> company= inventory_List_Service2.getDistinctCompany();
		        req.setAttribute("company", company);
		        
		        //저장 위치 가져오기
		        List<String> location= inventory_List_Service2.getDistinctLocation();
		        req.setAttribute("location", location);
		        
		        int countProduct = inventory_List_Service2.countProduct();
		        req.setAttribute("countProduct", countProduct);
		        
		    } catch (NumberFormatException e) {
		        System.out.println("NumberFormatException: " + e.getMessage());
		    } catch (IdNotFoundException e) {
		        System.out.println("IdNotFoundException: " + e.getMessage());
		    } catch (Exception e) {
		        e.printStackTrace();  // 로그 확인 필수
		    }

		    return "/WEB-INF/view/inventoryListPage.jsp";  // 예외 발생해도 무조건 JSP로 이동
		}

}
