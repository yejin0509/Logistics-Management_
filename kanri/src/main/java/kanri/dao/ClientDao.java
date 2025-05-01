package kanri.dao;

import java.sql.*;
import jdbc.JdbcUtil;
import kanri.model.Client;

public class ClientDao {

    // 클라이언트 ID로 클라이언트 정보를 조회하는 메서드
    // クライアントIDでクライアント情報を取得するメソッド
    public Client selectById(Connection conn, String client_Id) throws SQLException {
        // 클라이언트 ID를 이용해 정보를 조회하는 SQL 문
        // クライアントIDを使用して情報を取得するSQL文
        String sql = "SELECT * FROM client WHERE client_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client_Id);  // 쿼리에 클라이언트 ID를 세팅
            // 쿼리 실행 후 결과를 받음
            // クエリを実行して結果を取得
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 결과가 있으면 클라이언트 객체 생성하여 반환
                    // 結果があれば、クライアントオブジェクトを作成して返す
                    return new Client(
                        rs.getString("client_id"),
                        rs.getString("password"),
                        rs.getString("company"),
                        rs.getString("c_number"),
                        rs.getString("address")
                    );
                }
                return null;  // 결과가 없으면 null 반환
                // 結果がなければnullを返す
            }
        }
    }

    // 새로운 클라이언트를 데이터베이스에 추가하는 메서드
    // 新しいクライアントをデータベースに追加するメソッド
    public void insertClient(Connection conn, Client client) throws SQLException {
        // 클라이언트 정보를 추가하는 SQL 문
        // クライアント情報を追加するSQL文
        String sql = "INSERT INTO client (client_id, password, company, c_number, address) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 클라이언트 객체의 데이터를 쿼리문에 세팅
            // クライアントオブジェクトのデータをクエリに設定
            pstmt.setString(1, client.getClient_Id());
            pstmt.setString(2, client.getPassword());
            pstmt.setString(3, client.getCompany());
            pstmt.setString(4, client.getC_Number());
            pstmt.setString(5, client.getAddress());
            pstmt.executeUpdate();  // 쿼리 실행
        }
    }
    
    /* ChangePasswordService 오류 해결을 위해 추가된 메서드 */
    // ChangePasswordServiceのエラー解決のために追加されたメソッド
    public void update(Connection conn, Client client) throws SQLException {
        // 클라이언트 비밀번호를 업데이트하는 SQL 문
        // クライアントのパスワードを更新するSQL文
        String sql = "UPDATE client SET password = ? WHERE client_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 클라이언트 객체의 비밀번호를 세팅
            // クライアントオブジェクトのパスワードを設定
            pstmt.setString(1, client.getPassword());
            pstmt.setString(2, client.getClient_Id());
            pstmt.executeUpdate();  // 쿼리 실행
        }
    }
}
