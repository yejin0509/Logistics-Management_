package kanri.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.IdNotFoundException;
import kanri.service.InventoryListService2;
import mvc.command.CommandHandler;

// 아무것도 값이 들어가지 않은 채로 join된 쿼리를 불러오기 위해 만든 핸들러
// 初期状態または「初期化」ボタンをクリックしたときに動作する、結合された値が空の状態でクエリを呼び出すために作成されたハンドラー
public class NothingIncludeHandler implements CommandHandler {
    
    private InventoryListService2 inventory_List_Service2 = new InventoryListService2();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 핸들러가 실행되었을 때 로그 출력
        // ハンドラーが実行されたときのログ出力
        System.out.println("Handler executed: NothingIncludeHandler");

        try {
            // 인자 없이 받아온 서비스를 통해 재고 데이터를 가져와서 요청에 저장
            // 引数なしで受け取ったサービスを通じて在庫データを取得し、リクエストに保存
            List inventory_Data = inventory_List_Service2.get_Inventory_Product_List();
            req.setAttribute("inventory_Data", inventory_Data);

            // 상품 분류 가져오기 (중복 없이 가져오기)
            // 商品分類の取得（重複なしで取得）
            List<String> product_Type = inventory_List_Service2.getDistinctProductType();
            req.setAttribute("product_Type", product_Type);

            // 회사명 가져오기 (중복 없이 가져오기)
            // 会社名の取得（重複なしで取得）
            List<String> company = inventory_List_Service2.getDistinctCompany();
            req.setAttribute("company", company);

            // 저장 위치 가져오기 (중복 없이 가져오기)
            // 保存場所の取得（重複なしで取得）
            List<String> location = inventory_List_Service2.getDistinctLocation();
            req.setAttribute("location", location);

            // 전체 상품 개수 가져오기
            // 全商品の数を取得
            int countProduct = inventory_List_Service2.countProduct();
            req.setAttribute("countProduct", countProduct);

        } catch (NumberFormatException e) {
            // 숫자 형식 예외 처리
            // 数字フォーマット例外の処理
            System.out.println("NumberFormatException: " + e.getMessage());
        } catch (IdNotFoundException e) {
            // ID를 찾을 수 없는 예외 처리
            // IDが見つからない例外の処理
            System.out.println("IdNotFoundException: " + e.getMessage());
        } catch (Exception e) {
            // 기타 예외 처리
            // その他の例外処理
            e.printStackTrace();  // 로그에서 자세히 확인
        }

        // 예외가 발생해도 무조건 inventoryListPage.jsp로 이동
        // 例外が発生しても、必ずinventoryListPage.jspに遷移する
        return "/WEB-INF/view/inventoryListPage.jsp";
    }
}
