package kanri.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.dao.ProductDao;
import kanri.model.Manager;
import kanri.model.Product;

/**
 * Servlet implementation class OrderFormHandler
 */
@WebServlet("/manager_order")
public class InOrderFormHandler extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		/*
		 * Manager manager = (Manager) request.getSession().getAttribute("authManager");
		 * String managerId = manager.getManager_Id();
		 * System.out.println("managerId: 관리자 ID = " + managerId);
		 */
		
        List<Product> product_List = ProductDao.getAllProducts(); // DAO 호출
        if (product_List == null || product_List.isEmpty()) {
            System.out.println("상품 리스트가 비어있거나 null입니다.");
        }

        request.setAttribute("productList", product_List);        // JSP에 전달
		request.getRequestDispatcher("/manager_order.jsp").forward(request, response);
	 

	}

}
