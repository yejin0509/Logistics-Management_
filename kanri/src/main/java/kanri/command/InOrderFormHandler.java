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
 * 주문 등록 폼을 보여주는 서블릿
 * 注文登録フォームを表示するサーブレット（ちゅうもん とうろく フォーム）
 */
@WebServlet("/manager_order")
public class InOrderFormHandler extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		/*
		 * 로그인된 관리자 정보 가져오기 (사용 안 함 - 추후 사용 가능)
		 * ログイン中の管理者情報を取得（つかっていないが後で使う可能性あり）
		 * 
		 * Manager manager = (Manager) request.getSession().getAttribute("authManager");
		 * String managerId = manager.getManager_Id();
		 * System.out.println("managerId: 관리자 ID = " + managerId);
		 */

        // 전체 상품 리스트 가져오기
        // 商品リストを全件取得（しょうひん リスト を ぜんけん しゅとく）
        List<Product> product_List = ProductDao.getAllProducts(); // DAO 호출
        // DAO（データアクセスオブジェクト）を呼び出す（よびだす）

        if (product_List == null || product_List.isEmpty()) {
            System.out.println("상품 리스트가 비어있거나 null입니다.");
            // 商品リストが空またはnull（から または ヌル）
        }

        // 상품 목록을 request에 저장하여 JSP에서 사용 가능하도록 함
        // 商品リストをJSPで使えるようにリクエストへ保存（ほぞん）
        request.setAttribute("productList", product_List);

        // 주문 입력 JSP 페이지로 포워딩
        // 注文登録ページへフォワード（ちゅうもん とうろく ページ）
		request.getRequestDispatcher("/manager_order.jsp").forward(request, response);
	}
}
