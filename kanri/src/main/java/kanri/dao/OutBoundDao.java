/**
 * 아웃바운드 관련 DAO 클래스
 * アウトバウンド関連のDAOクラス
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
import kanri.model.OutBound;

public class OutBoundDao {

    // 아웃바운드 테이블의 총 레코드 수 조회
    // アウトバウンドテーブルの総レコード数を取得
    public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT (*) FROM OUTBOUND"); // OUTBOUND 테이블의 레코드 수 조회
            if (rs.next()) {
                return rs.getInt(1); // 조회한 레코드 수 반환
            }
            return 0; // 레코드가 없으면 0 반환
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    // 아웃바운드 테이블의 모든 레코드 조회
    // アウトバウンドテーブルの全てのレコードを取得
    public List<OutBound> select(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM OUTBOUND"); // OUTBOUND 테이블에서 모든 데이터 조회
            List<OutBound> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertOutBoundDao(rs)); // 각 레코드를 OutBound 객체로 변환하여 리스트에 추가
            }
            return result; // 결과 리스트 반환
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    // ResultSet을 OutBound 객체로 변환
    // ResultSetをOutBoundオブジェクトに変換
    private OutBound convertOutBoundDao(ResultSet rs) throws SQLException {
        return new OutBound(
                rs.getString("client_Id"),
                rs.getString("product_Id"),
                toDate(rs.getTimestamp("out_Date")),
                rs.getInt("out_Count"),
                rs.getInt("price")
        );
    }

    // 특정 날짜에 해당하는 아웃바운드 레코드 조회
    // 特定の日付に該当するアウトバウンドレコードを取得
    public List<OutBound> selectByDate(Connection conn, Date out_Date) throws SQLException {
        String sql = "SELECT * FROM OUTBOUND WHERE OUT_DATE = TO_DATE(?, 'YYYY-MM-DD') ORDER BY OUT_DATE DESC"; // 날짜로 아웃바운드 레코드 조회
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(1, sdf.format(out_Date)); // 날짜를 형식에 맞게 설정

            ResultSet rs = pstmt.executeQuery();
            List<OutBound> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertOutBoundDao(rs)); // 레코드를 OutBound 객체로 변환하여 리스트에 추가
            }
            return result; // 결과 리스트 반환
        }
    }

    // 특정 날짜에 해당하는 아웃바운드 레코드 수 조회
    // 特定の日付に該当するアウトバウンドレコードの数を取得
    public int selectCountByDate(Connection conn, Date out_Date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM OUTBOUND WHERE OUT_DATE = TO_DATE(?, 'YYYY-MM-DD')"; // 날짜에 해당하는 레코드 수 조회
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(out_Date.getTime())); // 날짜를 쿼리에 맞게 설정
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // 레코드 수 반환
            }
            return 0; // 레코드가 없으면 0 반환
        }
    }

    // Timestamp를 Date로 변환
    // TimestampをDateに変換
    private Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    // java.sql.Date를 Timestamp로 변환
    // java.sql.DateをTimestampに変換
    private Timestamp toTimestamp(java.sql.Date date) {
        return new Timestamp(date.getTime());
    }
}
