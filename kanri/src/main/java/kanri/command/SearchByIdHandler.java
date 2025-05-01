package kanri.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

public class SearchByIdHandler implements CommandHandler {
    
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
        System.out.println("Handler executed: SearchByIdHandler");

        try {
            // 클라이언트로부터 전달된 상품 ID 받기
            // クライアントから送られた商品IDを取得
            String product_Id = req.getParameter("product_Id");
            
            // 어디까지 작동했는지 확인하려고 넣은 코드
            // どこまで処理されたかを確認するためのデバッグ用コード
            System.out.println("Received product_Id: " + product_Id);

            // 상품 ID가 유효하지 않으면 예외 발생
            // 商品IDが無効であれば、例外を発生させる
            if (product_Id == null || product_Id.trim().isEmpty()) {
                // 상품 ID가 없을 경우, 사용자 정의 예외 발생
                // 商品IDがない場合、カスタム例外を発生させる
                throw new IdNotFoundException();  // 상품 ID 찾을 수 없다는 예외
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

            // 총 상품 수 가져오기
            // 商品の総数を取得
            int countProduct = inventory_List_Service.countProduct();
            req.setAttribute("countProduct", countProduct);

            // 상품 목록 가져오기
            // 商品リストを取得
            List inventory_Data = inventory_List_Service.get_Inventory_Product_List(product_Id);
            
            // 검색된 상품 ID와 상품 목록을 저장
            // 検索された商品IDと商品リストを保存
            req.setAttribute("search_Product_Id", product_Id);
            req.setAttribute("inventory_Data", inventory_Data);

        } catch (NumberFormatException e) {
            // 상품 ID가 숫자가 아닐 경우 예외 처리
            // 商品IDが数字でない場合の例外処理
            req.setAttribute("errorMessage", "상품 ID는 숫자여야 합니다.");
            System.out.println("NumberFormatException: " + e.getMessage());
        } catch (IdNotFoundException e) {
            // 상품 ID를 찾을 수 없을 경우 예외 처리
            // 商品IDが見つからない場合の例外処理
            req.setAttribute("errorMessage", "해당 ID의 상품을 찾을 수 없습니다.");
            System.out.println("IdNotFoundException: " + e.getMessage());
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
