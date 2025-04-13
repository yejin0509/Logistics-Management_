package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.OutBound;

public class ClientOrderDao {
	PreparedStatement pstmt = null;

    //발주 신청 시 DB insert
	public void insert(Connection conn, OutBound outbound) throws SQLException{
		String sql = "INSERT INTO OUTBOUND (client_id, product_id, out_date, out_count, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, outbound.getClient_Id());
            stmt.setString(2, outbound.getProduct_Id());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // 오늘 날짜
            stmt.setInt(4, outbound.getOut_Count());
            stmt.setInt(5, outbound.getPrice());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
	}
}
