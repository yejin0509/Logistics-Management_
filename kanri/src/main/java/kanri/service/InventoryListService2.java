package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;

public class InventoryListService2 {

	public List<InventoryProduct> get_Inventory_Product_List() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// product_Id 파라미터는 이제 사용하지 않고 모든 상품 목록을 가져옴
			List<InventoryProduct> inventoryList = (List<InventoryProduct>) KanriDao.selectByAll(conn);
			// InventoryListService와 다르게 2는 이미 받을 때 List형으로 변환을 했기 때문에
			// 반환할 때 형 변환이 없습니다
			return inventoryList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// 제품 분류 값만 가져오기
	public List<String> getDistinctProductType() {
	    try (Connection conn = ConnectionProvider.getConnection()) {
	        return KanriDao.selectDistinctProductType(conn);
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	// 제품 이름 값만 가져오기
	public List<String> getDistinctCompany() {
	    try (Connection conn = ConnectionProvider.getConnection()) {
	        return KanriDao.selectDistinctCompany(conn);
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	// 회사 이름 값만 가져오기
		public List<String> getDistinctLocation() {
		    try (Connection conn = ConnectionProvider.getConnection()) {
		        return KanriDao.selectDistinctLocation(conn);
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
		}
}