package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kanri.model.InventoryProduct;

public class KanriDao {

	// 상품 ID로 상품 정보를 조회하는 DAO
	public static InventoryProduct selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 실행할 쿼리문
		String query = "SELECT p.product_id, p.product_type, p.product_name, p.company, p.price, "
				+ "p.content, i.location, i.stock FROM product p INNER JOIN inventory i "
				+ "ON p.product_id = i.product_id where p.product_id = ? ORDER BY LENGTH(p.product_Id), p.product_Id ASC";

		try {
			pstmt = conn.prepareStatement(query);
			// 들어온 값을 첫번째 ?에 집어넣기
			pstmt.setInt(1, no);
			// query문 실행
			rs = pstmt.executeQuery();
			InventoryProduct inventory_Product = null;

			// query실행으로 나온 값을 inventory_Product에 형식에 맞게 순차 저장
			if (rs.next()) {
				inventory_Product = new InventoryProduct(rs.getString("product_Id"), rs.getString("product_Type"),
						rs.getString("product_Name"), rs.getString("company"), rs.getInt("price"),
						rs.getString("content"), rs.getString("location"), rs.getInt("stock"));

			}
			// 저장한 형태를 반환
			return inventory_Product;

			// rs랑 pstmt 다 썼으니 꺼줘야 함
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// TODO: handle finally clause
		}
	}

	// 기본값으로 모든 데이터 조회 DAO
	// 위에랑 형식은 같으니 참고할 것
	public static List<InventoryProduct> selectByAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT p.product_id, p.product_type, p.product_name, p.company, p.price, "
				+ "p.content, i.location, i.stock FROM product p INNER JOIN inventory i "
				+ "ON p.product_id = i.product_id ORDER BY LENGTH(p.product_Id), p.product_Id ASC";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			List<InventoryProduct> inventory_List = new ArrayList<>();

			while (rs.next()) {
				InventoryProduct inventory_Product = new InventoryProduct(rs.getString("product_Id"),
						rs.getString("product_Type"), rs.getString("product_Name"), rs.getString("company"),
						rs.getInt("price"), rs.getString("content"), rs.getString("location"), rs.getInt("stock"));

				// 저장 영역에 값 넣기
				// 위에랑 형태가 다른 이유는 받는 곳에서 형태가 달라져서 그렇습니다
				inventory_List.add(inventory_Product);
			}

			return inventory_List;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// TODO: handle finally clause
		}
	}

	// 제품 분류 값 가져오기
	public static List<String> selectDistinctProductType(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT product_type FROM product ORDER BY product_type ASC";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			List<String> productTypes = new ArrayList<>();

			while (rs.next()) {
				productTypes.add(rs.getString("product_type"));
			}

			return productTypes;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 회사 이름 값 가져오기
	public static List<String> selectDistinctCompany(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT company FROM product ORDER BY company ASC";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			List<String> company = new ArrayList<>();

			while (rs.next()) {
				company.add(rs.getString("company"));
			}

			return company;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 저장 위치 값 가져오기
	public static List<String> selectDistinctLocation(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT location FROM inventory ORDER BY location ASC";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			List<String> location = new ArrayList<>();

			while (rs.next()) {
				location.add(rs.getString("location"));
			}

			return location;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public static List<InventoryProduct> searchByElse(String product_Type, String product_Name, String company,
			String location, int max_Price) {
		List<InventoryProduct> inventory_List = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();

			// 기본 SQL 생성 (조건에 따라 where 절 추가)
			String sql = "SELECT p.product_id, p.product_type, p.product_name, p.company, p.price, p.content, i.location, i.stock FROM product p INNER JOIN inventory i ON p.product_id = i.product_id where 1=1";

			if (product_Type != null && !product_Type.equals("all")) {
				sql += " AND product_Type = ?";
			}
			if (product_Name != null && !product_Name.isEmpty()) {
				sql += " AND product_Name LIKE ?";
			}
			if (company != null && !company.equals("all")) {
				sql += " AND company = ?";
			}
			if (location != null && !location.equals("all")) {
				sql += " AND location = ?";
			}
			if (max_Price > 0) {
				sql += " AND price <= ?";
			}

			pstmt = conn.prepareStatement(sql);

			System.out.println("Generated SQL Query: " + sql);
			// 파라미터 바인딩
			int index = 1;
			if (product_Type != null && !product_Type.equals("all")) {
			    pstmt.setString(index++, product_Type);
			}
			if (product_Name != null && !product_Name.isEmpty()) {
			    pstmt.setString(index++, "%" + product_Name + "%");
			}
			if (company != null && !company.equals("all")) {
			    pstmt.setString(index++, company);
			}
			if (location != null && !location.equals("all")) {
			    pstmt.setString(index++, location);
			}
			if (max_Price > 0) {
			    pstmt.setInt(index++, max_Price);
			}

			rs = pstmt.executeQuery();

			// 결과 처리
			while (rs.next()) {
				InventoryProduct item = new InventoryProduct();
				item.setProduct_Id(rs.getString("product_Id"));
				item.setProduct_Type(rs.getString("product_Type"));
				item.setProduct_Name(rs.getString("product_Name"));
				item.setCompany(rs.getString("company"));
				item.setPrice(rs.getInt("price"));
				item.setLocation(rs.getString("location"));
				item.setStock(rs.getInt("stock"));
				item.setContent(rs.getString("content"));
				inventory_List.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
		return inventory_List;
	}
}
