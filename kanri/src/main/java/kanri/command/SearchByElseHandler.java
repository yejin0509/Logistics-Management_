package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.InventoryListElseService;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

public class SearchByElseHandler implements CommandHandler {
    
    // InventoryListElseService는 다른 조건을 이용해 상품 목록을 가져오는 서비스
    // InventoryListElseServiceは他の条件を使用して商品リストを取得するサービス
    private InventoryListElseService inventory_List_Else_Service = new InventoryListElseService();
    
    // InventoryListService2는 기본적인 상품 목록을 가져오는 서비스
    // InventoryListService2は基本的な商品リストを取得するサービス
    private InventoryListService2 inventory_List_Service2 = new InventoryListService2();
    
    // 검색 조건을 저장하는 변수들
    // 検索条件を格納する変数
    String product_Type = null;
    String product_Name = null;
    String company = null;
    String location = null;
    int max_Price = 0;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        // 핸들러 실행 로그
        // ハンドラー実行ログ
        System.out.println("Handler executed: SearchByElseHandler");

        try {
            // 기존 데이터 초기화 (null로 설정하여 이전 검색 데이터를 지움)
            // 以前の検索データをクリアするためにnullに設定
            req.setAttribute("inventory_Data", null);

            // 검색 조건을 파라미터에서 받아오기
            // 検索条件をリクエストパラメータから取得
            String product_Type = req.getParameter("product_Type");
            String product_Name = req.getParameter("product_Name");
            String company = req.getParameter("company");
            String location = req.getParameter("location");
            String max_Amount = req.getParameter("max_Amount");
            
            // 최대 가격이 지정된 경우 이를 정수로 변환
            // 最大価格が指定されている場合、整数に変換
            int max_Price = 0;
            if (max_Amount != null && !max_Amount.isEmpty()) {
                max_Price = Integer.parseInt(max_Amount);  // 최대 가격으로 설정
            }

            // 조건에 맞는 상품 리스트를 서비스에서 가져옴
            // 条件に一致する商品リストをサービスから取得
            List inventory_Data = inventory_List_Else_Service.get_Inventory_Product_List(product_Type, company, location);

            // 상품 분류 (중복 없는 목록) 가져오기
            // 商品分類（重複なし）を取得
            List<String> product_Type2 = inventory_List_Service2.getDistinctProductType();
            req.setAttribute("product_Type", product_Type2);

            // 회사명 (중복 없는 목록) 가져오기
            // 会社名（重複なし）を取得
            List<String> company2 = inventory_List_Service2.getDistinctCompany();
            req.setAttribute("company", company2);

            // 저장 위치 (중복 없는 목록) 가져오기
            // 保存場所（重複なし）を取得
            List<String> location2 = inventory_List_Service2.getDistinctLocation();
            req.setAttribute("location", location2);

            // 상품 총 개수 가져오기
            // 商品総数を取得
            int countProduct = inventory_List_Service2.countProduct();
            req.setAttribute("countProduct", countProduct);

            // 검색된 상품 데이터 저장
            // 検索された商品データを保存
            req.setAttribute("inventory_Data", inventory_Data);

            // 검색 조건 로그 출력
            // 検索条件のログ出力
            System.out.println("product_Type = " + product_Type);
            System.out.println("location : " + location);

        } catch (Exception e) {
            // 예외 발생 시 스택 트레이스를 출력하여 로그 확인
            // 例外が発生した場合、スタックトレースを出力してログを確認
            e.printStackTrace();  // 必ずログを確認すること
        }

        // 검색 결과를 보여줄 JSP 페이지로 포워딩
        // 検索結果を表示するJSPページにフォワード
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/inventoryListPage.jsp");
        dispatcher.forward(req, res);  // 결과 페이지로 포워딩

        // forward 했기 때문에 반환 값은 null
        // フォワードしたため、戻り値はnull
        return null;
    }
}
