package kanri.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

public class SearchByNameHandler implements CommandHandler {
    
    // 서비스 불러오기
    // サービスを呼び出す
    private InventoryListService inventory_List_Service = new InventoryListService();
    private InventoryListService2 inventory_List_Service2 = new InventoryListService2();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        // 이미 요청이 처리되었는지 확인하는 조건 (중복 처리 방지)
        // リクエストがすでに処理されたかを確認する条件（重複処理防止）
        if (req.getAttribute("alreadyForwarded") != null) {
            return null; // 이미 처리된 요청이라면 더 이상 진행하지 않음
            // すでに処理されたリクエストなら、これ以上進行しない
        }
        
        req.setAttribute("alreadyForwarded", true); // 요청을 처리했음을 표시
        // リクエストを処理したことを示すフラグを設定

        // 어디까지 작동했는지 확인하려고 넣은 코드
        // どこまで処理されたかを確認するためのデバッグ用コード
        System.out.println("Handler executed: SearchByNameHandler");

        try {
            // 클라이언트로부터 전달된 상품 이름 받기
            // クライアントから送られた商品名を取得
            String product_Name = req.getParameter("product_Name");
            
            // 어디까지 작동했는지 확인하려고 넣은 코드
            // どこまで処理されたかを確認するためのデバッグ用コード
            System.out.println("Received product_Name: " + product_Name);

            // 상품 이름이 유효하지 않으면 예외 발생
            // 商品名が無効であれば、例外を発生させる
            if (product_Name == null || product_Name.trim().isEmpty()) {
                // 상품 이름이 없을 경우, 사용자 정의 예외 발생
                // 商品名がない場合、カスタム例外を発生させる
                throw new IdNotFoundException();  // 상품 이름 찾을 수 없다는 예외
            }

            // 상품 분류 가져오기
            // 商品分類を取得
            List<String> product_Type2 = inventory_List_Service2.getDistinctProductType();
            req.setAttribute("product_Type", product_Type2);

            // 회사명 가져오기
            // 会社名を取得
            List<String> company2 = inventory_List_Service2.getDistinctCompany();
            req.setAttribute("company", company2);

            // 저장 위치 가져오기
            // 保管場所を取得
            List<String> location2 = inventory_List_Service2.getDistinctLocation();
            req.setAttribute("location", location2);

            // 상품 이름으로 상품 리스트 가져오기
            // 商品名で商品リストを取得
            List inventory_Data = inventory_List_Service.getInventoryProductListByName(product_Name);
            
            // 전체 상품 개수 가져오기
            // 商品の総数を取得
            int countProduct = inventory_List_Service.countProduct();
            req.setAttribute("countProduct", countProduct);

            // 검색된 상품 이름과 상품 목록을 저장
            // 検索された商品名と商品リストを保存
            req.setAttribute("search_Product_Name", product_Name);
            req.setAttribute("inventory_Data", inventory_Data);

        } catch (Exception e) {
            // 예기치 못한 오류 발생 시 처리
            // 予期しないエラーが発生した場合の処理
            req.setAttribute("errorMessage", "예상치 못한 오류가 발생했습니다.");
            e.printStackTrace(); // 로그 확인 필수
        }

        // 최종적으로 JSP 페이지로 포워딩
        // 最終的にJSPページにフォワード
        return "/WEB-INF/view/inventoryListPage.jsp";
    }

}
