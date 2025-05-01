package kanri.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.model.InBound;
import kanri.model.Manager;
import kanri.service.InboundService;

/**
 * 입고(발주) 처리용 서블릿
 * 入庫（発注）処理用サーブレット（にゅうこ・はっちゅう）
 */
@WebServlet("/InOrderHandler")
public class InOrderHandler extends HttpServlet {

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트로부터 전달받은 상품 ID, 수량, 가격 배열
        // 商品ID・数量・価格を取得（しょうひん・すうりょう・かかく）
        String[] product_Ids = request.getParameterValues("productId");  // 또는 productName → ID 매핑 가능
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("productPrice");

        // 세션에서 로그인된 관리자 정보 가져오기
        // ログイン中の管理者情報を取得（かんりしゃ じょうほう）
        Manager manager = (Manager) request.getSession().getAttribute("authManager");
        String managerId = manager.getManager_Id();

        // 입고 목록 생성
        // 入庫リストを作成（にゅうこ リスト）
        List<InBound> inbound_List = new ArrayList<>();

        for (int i = 0; i < product_Ids.length; i++) {
            InBound ob = new InBound();
            ob.setManager_Id(managerId);  // 관리자 ID 설정
            ob.setProduct_Id(product_Ids[i]);  // 상품 ID 설정
            ob.setIn_Date(new java.sql.Date(System.currentTimeMillis()));  // 현재 날짜 설정
            ob.setIn_Count(Integer.parseInt(quantities[i]));  // 수량 설정
            ob.setPrice(Integer.parseInt(prices[i]));  // 가격 설정
            inbound_List.add(ob);  // 리스트에 추가
        }

        // DB 저장 서비스 호출
        // データベース保存サービスを呼び出し（ほぞん）
        InboundService service = new InboundService();

        try {
            // 입고 목록 저장 시도
            // 入庫データを保存（にゅうこ）
            service.saveOutboundList(inbound_List);

            // 성공 시: 세션에 ID 저장하고 성공 페이지로 이동
            // 成功したらセッションに保存して成功ページへ（せいこう）
            request.getSession().setAttribute("Id", "managerId");
            request.getRequestDispatcher("boundSuccess.jsp").forward(request, response);

        } catch (Exception e) {
            // 실패 시: 예외 출력 후 실패 페이지로 이동
            // 失敗したら例外を出力し、失敗ページへ（しっぱい）
            e.printStackTrace();
            response.sendRedirect("boundFail.jsp");
        }
    }
}
