package kanri.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.KanriDao;
import kanri.model.InventoryProduct;

public class InventoryListElseService {

	public List<InventoryProduct> get_Inventory_Product_List(String productType, String productName, String company, String location,
			int maxPrice) {

		String product_Type = null;
		String product_Name = null;
		int max_Price = 0;

		try (Connection conn = ConnectionProvider.getConnection()) {
			List<InventoryProduct> inventory_Product = KanriDao.searchByElse(product_Type, product_Name, company,
					location, max_Price);

			return inventory_Product;

			// InventoryListService2는 DAO에서 형 변환을 미리 해서 이 부분이 다름

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
