package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.Manager;

public class ManagerDao {

	// ID로 관리자 조회 (중복 체크용)
	public Manager selectById(Connection conn, String manager_Id) throws SQLException {
		String sql = "SELECT * FROM Manager WHERE manager_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, manager_Id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Manager(
						rs.getString("manager_id"),
						rs.getString("password")
					);
				}
				return null;
			}
		}
	}

	// 관리자 등록
	public void insert(Connection conn, Manager manager) throws SQLException {
		String sql = "INSERT INTO Manager (manager_id, password) VALUES (?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, manager.getManager_Id());
			pstmt.setString(2, manager.getPassword());
			pstmt.executeUpdate();
		}
	}
}
