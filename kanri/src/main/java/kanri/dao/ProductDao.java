package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.model.Product;

public class ProductDao {

	// 상품 ID로 상품 정보를 조회하는 메소드
	public static Product getProductById(String productId) {
		Product product = null;
		String query = "SELECT * FROM products WHERE product_id = ?";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, productId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					product = new Product();
					product.setProduct_id(rs.getString("product_id"));
					product.setProduct_type(rs.getString("product_type"));
					product.setProduct_name(rs.getString("product_name"));
					product.setCompany(rs.getString("company"));
					product.setPrice(rs.getInt("price"));
					product.setContent(rs.getString("content"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
    public static List<Product> getProductsByConditions(String productType, String productName, String company, String location, int maxAmount) {
        List<Product> products = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");

        // 조건에 따라 쿼리 동적으로 생성
        if (productType != null && !productType.equals("All")) {
            query.append(" AND product_type = ?");
        }
        if (productName != null && !productName.isEmpty()) {
            query.append(" AND product_name LIKE ?");
        }
        if (company != null && !company.equals("All")) {
            query.append(" AND company = ?");
        }
        if (location != null && !location.equals("All")) {
            query.append(" AND location = ?");
        }
        if (maxAmount > 0) {
            query.append(" AND price <= ?");
        }

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            int index = 1;

            // 조건에 따라 파라미터 설정
            if (productType != null && !productType.equals("All")) {
                pstmt.setString(index++, productType);
            }
            if (productName != null && !productName.isEmpty()) {
                pstmt.setString(index++, "%" + productName + "%");
            }
            if (company != null && !company.equals("All")) {
                pstmt.setString(index++, company);
            }
            if (location != null && !location.equals("All")) {
                pstmt.setString(index++, location);
            }
            if (maxAmount > 0) {
                pstmt.setInt(index++, maxAmount);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProduct_id(rs.getString("product_id"));
                    product.setProduct_type(rs.getString("product_type"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setCompany(rs.getString("company"));
                    product.setPrice(rs.getInt("price"));
                    product.setContent(rs.getString("content"));
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}