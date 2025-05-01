package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.InBound;

public class ManagerOrderDao {
    PreparedStatement pstmt = null;

    // 발주 신청 시 DB insert
    // 発注申請時にDBへinsertする
    public void insert(Connection conn, InBound inbound) throws SQLException {
        // 실행할 SQL 쿼리문
        // 実行するSQLクエリ
        String sql = "INSERT INTO INBOUND (manager_id, product_id, in_date, in_count, price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 쿼리문에 값 넣기
            // クエリに値をセット
            stmt.setString(1, inbound.getManager_Id()); // 관리자 ID
            stmt.setString(2, inbound.getProduct_Id()); // 제품 ID
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // 오늘 날짜
            // 現在の日付をセット
            stmt.setInt(4, inbound.getIn_Count()); // 입고 수량
            stmt.setInt(5, inbound.getPrice()); // 가격

            // 쿼리 실행
            // クエリを実行
            stmt.executeUpdate();
        } finally {
            // pstmt 리소스 종료
            // pstmtリソースを閉じる
            JdbcUtil.close(pstmt);
        }
    }
}
