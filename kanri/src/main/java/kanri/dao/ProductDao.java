package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.model.Product;

public class ProductDao {
	
	// 전체 상품 리스트 조회 (상품명, ID, 가격 등 가져오기용)
	//client_order 페이지에 상품 출력을 위함
	public static List<Product> getAllProducts() {
	    List<Product> products = new ArrayList<>();
	    String query = "SELECT product_Id, product_Name, price FROM product";

	    try (Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {
	    	

	        while (rs.next()) {
	            Product product = new Product();
	            product.setProduct_Id(rs.getString("product_Id"));
	            product.setProduct_Name(rs.getString("product_Name"));
	            product.setPrice(rs.getInt("price"));
	            products.add(product);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return products;
	}


	//client_order 페이지에 상품 출력을 위함
	// 상품 ID로 상품 정보를 조회하는 메소드
	public static Product getProductById(String product_Id) {
		Product product = null;
		String query = "SELECT * FROM products WHERE product_Id = ?";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, product_Id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					product = new Product();
					product.setProduct_Id(rs.getString("product_Id"));
					product.setProduct_Type(rs.getString("product_Type"));
					product.setProduct_Name(rs.getString("product_Name"));
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

	public static List<Product> getProductsByConditions(String product_Type, String product_Name, String company,
			String location, int max_Amount) {
		List<Product> products = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");

		// 조건에 따라 쿼리 동적으로 생성
		if (product_Type != null && !product_Type.equals("All")) {
			query.append(" AND product_type = ?");
		}
		if (product_Name != null && !product_Name.isEmpty()) {
			query.append(" AND product_name LIKE ?");
		}
		if (company != null && !company.equals("All")) {
			query.append(" AND company = ?");
		}
		if (location != null && !location.equals("All")) {
			query.append(" AND location = ?");
		}
		if (max_Amount > 0) {
			query.append(" AND price <= ?");
		}

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

			int index = 1;

			// 조건에 따라 파라미터 설정
			if (product_Type != null && !product_Type.equals("All")) {
				pstmt.setString(index++, product_Type);
			}
			if (product_Name != null && !product_Name.isEmpty()) {
				pstmt.setString(index++, "%" + product_Name + "%");
			}
			if (company != null && !company.equals("All")) {
				pstmt.setString(index++, company);
			}
			if (location != null && !location.equals("All")) {
				pstmt.setString(index++, location);
			}
			if (max_Amount > 0) {
				pstmt.setInt(index++, max_Amount);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Product product = new Product();
					product.setProduct_Id(rs.getString("product_Id"));
					product.setProduct_Type(rs.getString("product_Type"));
					product.setProduct_Name(rs.getString("product_Name"));
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