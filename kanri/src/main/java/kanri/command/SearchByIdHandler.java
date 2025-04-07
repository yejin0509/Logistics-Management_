package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.dao.ProductDao;
import kanri.model.Product;
import mvc.command.CommandHandler;

public class SearchByIdHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 폼에서 전달받은 데이터 처리
		String productId = req.getParameter("search_product_id");

		// DB 조회 등 비즈니스 로직 실행
		Product product = ProductDao.getProductById(productId);

		// 조회된 결과를 request 객체에 저장
		req.setAttribute("product", product);

		// 결과를 보여줄 JSP 파일 경로 반환
		return "/inventoryListPage.jsp";
	}
}
