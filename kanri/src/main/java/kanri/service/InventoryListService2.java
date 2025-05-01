package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;

public class InventoryListService2 {

    // 모든 상품 목록을 가져오는 메소드
    // すべての製品リストを取得するメソッド
	public List<InventoryProduct> get_Inventory_Product_List() {
		try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
            // product_Id 파라미터는 이제 사용하지 않고 모든 상품 목록을 가져옴
            // product_Idパラメータはもはや使用せず、すべての製品リストを取得します
			List<InventoryProduct> inventoryList = (List<InventoryProduct>) KanriDao.selectByAll(conn);
			// InventoryListService와 다르게 2는 이미 받을 때 List형으로 변환을 했기 때문에
            // 반환할 때 형 변환이 없습니다
            // InventoryListServiceとは異なり、2では受け取る際にList型に変換しているため、
            // 戻す際には型変換がありません
			return inventoryList; // 재고 제품 목록 반환
            // 在庫製品のリストを返す
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
		}
	}
    
    // 제품 분류 값만 가져오기
    // 製品分類値のみを取得するメソッド
	public List<String> getDistinctProductType() {
	    try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
	        return KanriDao.selectDistinctProductType(conn); // 제품 분류 목록 반환
            // 製品分類リストを返す
	    } catch (SQLException e) {
	        throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
	    }
	}

    // 제품 이름 값만 가져오기
    // 製品名値のみを取得するメソッド
	public List<String> getDistinctCompany() {
	    try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
	        return KanriDao.selectDistinctCompany(conn); // 회사 이름 목록 반환
            // 会社名リストを返す
	    } catch (SQLException e) {
	        throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
	    }
	}

    // 회사 위치 값만 가져오기
    // 会社の位置値のみを取得するメソッド
	public List<String> getDistinctLocation() {
	    try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
	        return KanriDao.selectDistinctLocation(conn); // 회사 위치 목록 반환
            // 会社の位置リストを返す
	    } catch (SQLException e) {
	        throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
	    }
	}
		
	// 전체 필드 개수 가져오기
	// 全フィールド数を取得するメソッド
	public int countProduct() {
	    try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
	        return KanriDao.countProduct(conn); // 상품 개수 반환
            // 商品数を返す
	    } catch (SQLException e) {
	        throw new RuntimeException(e); // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外を投げる
	    }
	}
}
