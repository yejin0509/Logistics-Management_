package kanri.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.dao.ProductDao;
import kanri.model.Product;
import mvc.command.CommandHandler;

public class SearchByElseHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 클라이언트가 입력한 검색 조건 가져오기
		String product_Type = req.getParameter("search_product_type");
		String product_Name = req.getParameter("search_product_name");
		String company = req.getParameter("search_company");
		String location = req.getParameter("search_location");
		String amount_Str = req.getParameter("search_amount");

		int max_Amount = 0;
		if (amount_Str != null && !amount_Str.isEmpty()) {
			max_Amount = Integer.parseInt(amount_Str);
		}

		// DAO를 통해 조건에 맞는 상품 리스트를 가져옴
		List<Product> products = ProductDao.getProductsByConditions(product_Type, product_Name, company, location,
				max_Amount);

		// 조회된 상품 리스트를 request 객체에 저장
		req.setAttribute("products", products);

		// JSP 페이지 경로 반환
		return "/inventoryListPage.jsp";
	}
}
