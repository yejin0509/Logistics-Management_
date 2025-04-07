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
		String productType = req.getParameter("search_product_type");
		String productName = req.getParameter("search_product_name");
		String company = req.getParameter("search_company");
		String location = req.getParameter("search_location");
		String amountStr = req.getParameter("search_amount");

		int maxAmount = 0;
		if (amountStr != null && !amountStr.isEmpty()) {
			maxAmount = Integer.parseInt(amountStr);
		}

		// DAO를 통해 조건에 맞는 상품 리스트를 가져옴
		List<Product> products = ProductDao.getProductsByConditions(productType, productName, company, location,
				maxAmount);

		// 조회된 상품 리스트를 request 객체에 저장
		req.setAttribute("products", products);

		// JSP 페이지 경로 반환
		return "/inventoryListPage.jsp";
	}
}
