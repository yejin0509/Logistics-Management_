package kanri.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.model.Client;
import kanri.model.OutBound;
import kanri.service.OutboundService;

@WebServlet("/OrderHandler")
public class OrderHandler extends HttpServlet {

    // POST 요청을 처리하는 메서드
    // POSTリクエストを処理するメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 클라이언트로부터 전달된 주문 정보 (상품 ID, 수량, 가격) 가져오기
        // クライアントから送信された注文情報（商品ID、数量、価格）を取得
        String[] product_Ids = request.getParameterValues("productId");  // 또는 productName -> ID 매핑
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("productPrice");

        // 세션에서 인증된 클라이언트 정보 가져오기
        // セッションから認証されたクライアント情報を取得
        Client client = (Client) request.getSession().getAttribute("authClient");
        String clientId = client.getClient_Id();

        // 발주 리스트를 담을 리스트 생성
        // 発注リストを格納するリストを作成
        List<OutBound> outbound_List = new ArrayList<>();

        // 각 상품에 대해 발주 정보를 생성하여 리스트에 추가
        // 各商品に対して発注情報を生成し、リストに追加
        for (int i = 0; i < product_Ids.length; i++) {
            OutBound ob = new OutBound();

            // 발주 정보 설정
            // 発注情報を設定
            ob.setClient_Id(clientId);  // 클라이언트 ID
            ob.setProduct_Id(product_Ids[i]);  // 상품 ID
            ob.setOut_Date(new java.sql.Date(System.currentTimeMillis()));  // 발주 날짜 (현재 날짜)
            ob.setOut_Count(Integer.parseInt(quantities[i]));  // 수량
            ob.setPrice(Integer.parseInt(prices[i]));  // 가격
            outbound_List.add(ob);  // 발주 리스트에 추가
        }

        // OutboundService를 통해 발주 리스트를 DB에 저장
        // OutboundServiceを通じて発注リストをDBに保存
        OutboundService service = new OutboundService();
        try {
            // 발주 리스트 저장 성공 시, 성공 페이지로 이동
            // 発注リストの保存が成功した場合、成功ページに遷移
            service.saveOutboundList(outbound_List);  // DB에 발주 리스트 저장
            request.getSession().setAttribute("Id", "clientId");  // 클라이언트 ID를 세션에 저장
            request.getRequestDispatcher("boundSuccess.jsp").forward(request, response);  // 발주 성공 페이지로 포워딩
        } catch (Exception e) {
            // 발주 저장 중 오류가 발생한 경우, 오류 페이지로 리다이렉트
            // 発注保存中にエラーが発生した場合、エラーページにリダイレクト
            e.printStackTrace();  // 예외 스택 트레이스 출력
            response.sendRedirect("boundFail.jsp");  // 발주 실패 페이지로 리다이렉트
        }
    }

}
