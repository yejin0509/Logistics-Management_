package kanri.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.dao.ProductDao;
import kanri.model.Product;

/**
 * Servlet implementation class OrderFormHandler
 * 주문 폼을 처리하는 서블릿 핸들러
 * 注文フォームを処理するサーブレットハンドラー
 */
@WebServlet("/client_order")  // 클라이언트의 "/client_order" URL에 매핑된 서블릿
// クライアントの"/client_order" URLにマッピングされたサーブレット
public class OrderFormHandler extends HttpServlet {

    // GET 요청을 처리하는 메서드
    // GETリクエストを処理するメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ProductDao를 사용하여 모든 상품 목록을 가져옴
        // ProductDaoを使用してすべての商品リストを取得
        List<Product> product_List = ProductDao.getAllProducts(); // DAO 호출

        // 상품 목록이 비어 있거나 null인 경우 로그 출력
        // 商品リストが空またはnullの場合、ログを出力
        if (product_List == null || product_List.isEmpty()) {
            System.out.println("상품 리스트가 비어있거나 null입니다.");
            // 商品リストが空またはnullです
        }

        // 가져온 상품 리스트를 요청에 속성으로 설정하여 JSP로 전달
        // 取得した商品リストをリクエスト属性として設定し、JSPに渡す
        request.setAttribute("productList", product_List); // JSP 페이지에 상품 리스트 전달

        // "/client_order.jsp" 페이지로 포워딩
        // "/client_order.jsp"ページにフォワード
        request.getRequestDispatcher("/client_order.jsp").forward(request, response);

    }

}
