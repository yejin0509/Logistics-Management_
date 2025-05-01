/**
 * 입고 관련 데이터베이스 작업을 처리하는 DAO 클래스
 * 入庫に関連するデータベース操作を処理するDAOクラス
 */
package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import kanri.model.InBound;

public class InBoundDao {

    // INBOUND 테이블의 총 레코드 수를 반환하는 메서드
    // INBOUNDテーブルのレコード総数を返すメソッド
    public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT (*) FROM INBOUND");
            if (rs.next()) {
                return rs.getInt(1);  // 첫 번째 컬럼의 값 (레코드 수)
            }
            return 0;
        } finally {
            JdbcUtil.close(rs);  // ResultSet 리소스 닫기
            JdbcUtil.close(stmt);  // Statement 리소스 닫기
        }
    }

    // INBOUND 테이블에서 모든 레코드를 조회하는 메서드
    // INBOUNDテーブルからすべてのレコードを取得するメソッド
    public List<InBound> select(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM INBOUND");
            List<InBound> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertInBoundDao(rs));  // ResultSet을 InBound 객체로 변환하여 리스트에 추가
            }
            return result;
        } finally {
            JdbcUtil.close(rs);  // ResultSet 리소스 닫기
            JdbcUtil.close(stmt);  // Statement 리소스 닫기
        }
    }

    // ResultSet에서 InBound 객체로 변환하는 메서드
    // ResultSetからInBoundオブジェクトに変換するメソッド
    private InBound convertInBoundDao(ResultSet rs) throws SQLException {
        return new InBound(
            rs.getString("manager_Id"),   // 관리자 ID
            rs.getString("product_Id"),   // 상품 ID
            toDate(rs.getTimestamp("in_Date")),  // 입고 날짜 (Timestamp -> Date 변환)
            rs.getInt("in_Count"),   // 입고 수량
            rs.getInt("price")   // 가격
        );
    }

    // 특정 날짜에 대한 입고 데이터를 조회하는 메서드
    // 特定の日付に関連する入庫データを取得するメソッド
    public List<InBound> selectByDate(Connection conn, Date in_Date) throws SQLException {
        String sql = "SELECT * FROM INBOUND WHERE IN_DATE = TO_DATE(?, 'YYYY-MM-DD') ORDER BY IN_DATE DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(1, sdf.format(in_Date));  // 날짜를 "yyyy-MM-dd" 형식으로 변환하여 설정
            
            ResultSet rs = pstmt.executeQuery();
            List<InBound> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertInBoundDao(rs));  // 조회한 데이터를 InBound 객체로 변환하여 리스트에 추가
            }
            
            return result;
        }
    }

    // 특정 날짜에 대한 입고 레코드 수를 조회하는 메서드
    // 特定の日付に関連する入庫レコードの数を取得するメソッド
    public int selectCountByDate(Connection conn, Date in_Date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INBOUND WHERE IN_DATE = TO_DATE(?, 'yyyy-MM-dd')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(in_Date.getTime()));  // java.util.Date를 java.sql.Date로 변환하여 설정
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // 입고 레코드의 개수 반환
            }
            return 0;
        }
    }

    // Timestamp를 Date 객체로 변환하는 메서드
    // TimestampをDateオブジェクトに変換するメソッド
    private Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    // java.sql.Date를 Timestamp 객체로 변환하는 메서드
    // java.sql.DateをTimestampオブジェクトに変換するメソッド
    private Timestamp toTimestamp(java.sql.Date date) {
        return new Timestamp(date.getTime());
    }
}
