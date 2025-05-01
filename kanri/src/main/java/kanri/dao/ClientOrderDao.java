package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.OutBound;

public class ClientOrderDao {
    PreparedStatement pstmt = null;

    // 발주 신청 시 DB에 발주 정보를 insert하는 메서드
    // 発注申請時にDBに発注情報を挿入するメソッド
    public void insert(Connection conn, OutBound outbound) throws SQLException {
        // 발주 정보를 INSERT하는 SQL 쿼리
        // 発注情報をINSERTするSQLクエリ
        String sql = "INSERT INTO OUTBOUND (client_id, product_id, out_date, out_count, price) VALUES (?, ?, ?, ?, ?)";
        
        // PreparedStatement를 사용하여 쿼리 실행
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, outbound.getClient_Id());  // 클라이언트 ID
            stmt.setString(2, outbound.getProduct_Id()); // 상품 ID
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // 발주 날짜 (현재 날짜)
            stmt.setInt(4, outbound.getOut_Count());  // 발주 수량
            stmt.setInt(5, outbound.getPrice());     // 발주 가격
            stmt.executeUpdate();  // 쿼리 실행 (INSERT)
        } finally {
            // 자원 반환 (pstmt는 try-with-resources에서 자동으로 닫히므로 추가적으로 닫을 필요 없음)
            // リソースを返す（pstmtはtry-with-resourcesで自動的に閉じられるため、追加で閉じる必要はありません）
            JdbcUtil.close(pstmt);  // 만약 추가적인 리소스가 있다면 닫는 작업을 수행
        }
    }
}
