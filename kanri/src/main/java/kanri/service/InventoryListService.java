package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;


public class InventoryListService {

    // 상품 ID에 해당하는 재고 제품을 조회하는 메소드
    // 商品IDに該当する在庫製品を検索するメソッド
	public List<InventoryProduct> get_Inventory_Product_List(String product_Id) {
		try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
            // DAO를 사용하여 상품 ID에 해당하는 재고 제품 조회
            // DAOを使用して商品IDに該当する在庫製品を検索
			InventoryProduct inventory_Product = KanriDao.selectById(conn, product_Id);
			if (inventory_Product == null) { // 해당 상품 ID가 없으면 예외 처리
				throw new IdNotFoundException(); // 아이디를 찾을 수 없을 때 예외 던짐
                // 商品IDが見つからない場合は例外を投げる
			}
			// List 형으로 감싸서 반환, copilot 좀 썼습니다
            // List型でラップして返す、copilotを少し使いました
			return List.of(inventory_Product); // 조회된 단일 재고 제품을 List로 반환
            // 検索された単一の在庫製品をListとして返す
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
		}
	}
    
    // 상품 이름에 해당하는 재고 제품을 조회하는 메소드
    // 商品名に該当する在庫製品を検索するメソッド
	public List<InventoryProduct> getInventoryProductListByName(String product_Name) {
		try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
			// DAO를 사용하여 상품 이름에 해당하는 재고 제품 조회
            // DAOを使用して商品名に該当する在庫製品を検索
			InventoryProduct inventory_Product = KanriDao.selectByName(conn, product_Name);
			// InventoryListService2는 DAO에서 형 변환을 미리 해서 이 부분이 다름
            // InventoryListService2はDAOで型変換を事前に行うため、この部分が異なる
			return List.of(inventory_Product); // 조회된 단일 재고 제품을 List로 반환
            // 検索された単一の在庫製品をListとして返す
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
		}
	}

    // 전체 상품 수를 조회하는 메소드
    // 全商品の数を検索するメソッド
	public int countProduct() {
	    try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
	        return KanriDao.countProduct(conn); // 상품 수를 조회하여 반환
            // 商品の数を検索して返す
	    } catch (SQLException e) {
	        throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
	    }
	}

}
