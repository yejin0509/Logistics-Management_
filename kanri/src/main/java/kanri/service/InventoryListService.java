package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;


public class InventoryListService {

	public List<InventoryProduct> get_Inventory_Product_List(String product_Id) {
		int product_Id2 = Integer.parseInt(product_Id);
		try (Connection conn = ConnectionProvider.getConnection()) {
			InventoryProduct inventory_Product = KanriDao.selectById(conn, product_Id2);
			if (inventory_Product == null) {
				throw new IdNotFoundException();
			}
			// List 형으로 감싸서 반환, copilot 좀 썼습니다
			//InventoryListService2는 DAO에서 형 변환을 미리 해서 이 부분이 다름
			return List.of(inventory_Product); 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
