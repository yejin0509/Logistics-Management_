package kanri.dao;

import java.sql.*;
import jdbc.JdbcUtil;
import kanri.model.Client;

public class ClientDao {

	public Client selectById(Connection conn, String client_Id) throws SQLException {
		String sql = "SELECT * FROM client WHERE client_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, client_Id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Client(
						rs.getString("client_id"),
						rs.getString("password"),
						rs.getString("company"),
						rs.getString("c_number"),
						rs.getString("address")
					);
				}
				return null;
			}
		}
	}

	public void insertClient(Connection conn, Client client) throws SQLException {
		String sql = "INSERT INTO client (client_id, password, company, c_number, address) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, client.getClient_Id());
			pstmt.setString(2, client.getPassword());
			pstmt.setString(3, client.getCompany());
			pstmt.setString(4, client.getC_Number());
			pstmt.setString(5, client.getAddress());
			pstmt.executeUpdate();
		}
	}
	
	/* ChangePasswordService 오류해결 위해 추가 */
	public void update(Connection conn, Client client) throws SQLException {
	    String sql = "UPDATE client SET password = ? WHERE client_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, client.getPassword());  // getter 이름은 클래스에 따라 다를 수 있음
	        pstmt.setString(2, client.getClient_Id()); // 또는 client.getId()
	        pstmt.executeUpdate();
	    }
	}

	
}
