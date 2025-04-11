package kanri.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.InBound;

public class ManagerOrderDao {
	PreparedStatement pstmt = null;

    //발주 신청 시 DB insert
	public void insert(Connection conn, InBound inbound) throws SQLException{
		String sql = "INSERT INTO INBOUND (client_id, product_id, in_date, in_count, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inbound.getClient_Id());
            stmt.setString(2, inbound.getProduct_Id());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // 오늘 날짜
            stmt.setInt(4, inbound.getIn_Count());
            stmt.setInt(5, inbound.getPrice());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
	}
}
