package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;

public class InventoryListElseService {

    // 상품 유형, 회사, 위치에 따른 재고 목록을 조회하는 메소드
    // 商品タイプ、会社、場所に基づいて在庫リストを取得するメソッド
	public List<InventoryProduct> get_Inventory_Product_List(String productType, String company, String location) {

		String product_Type = null; // productType 초기화 (사용되지 않음)
		// product_Typeの初期化（使用されていない）

		try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
            // DB에서 재고 제품 리스트를 조회하는 DAO 호출
            // DBから在庫製品リストを検索するDAOの呼び出し
			List<InventoryProduct> inventory_Product = KanriDao.searchByElse(product_Type, company, location);

			return inventory_Product; // 조회된 재고 제품 리스트 반환
			// 検索された在庫製品リストを返す

			// InventoryListService2는 DAO에서 형 변환을 미리 해서 이 부분이 다름
			// InventoryListService2はDAOで型変換を事前に行うため、この部分が異なる
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
			// 例外が発生した場合、ランタイム例外を投げる
		}
	}
}
