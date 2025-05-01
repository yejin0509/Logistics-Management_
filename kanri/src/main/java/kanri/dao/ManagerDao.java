package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kanri.model.Manager;

public class ManagerDao {

    // ID로 관리자 조회 (중복 체크용)
    // IDで管理者を検索（重複チェック用）
    public Manager selectById(Connection conn, String manager_Id) throws SQLException {
        String sql = "SELECT * FROM Manager WHERE manager_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, manager_Id);  
            // 입력된 manager_id로 쿼리 실행
            // 入力されたmanager_idでクエリを実行
            try (ResultSet rs = pstmt.executeQuery()) {  
            	// 쿼리 실행 후 결과 받기
                // クエリを実行して結果を取得
                if (rs.next()) {  // 결과가 존재하면
                    return new Manager(  // Manager 객체 생성 후 반환
                        rs.getString("manager_id"),  // manager_id 값
                        rs.getString("password")     // password 값
                    );
                }
                return null;  
                // 결과가 없으면 null 반환
                // 結果がない場合はnullを返す
            }
        }
    }

    // 관리자 등록
    // 管理者登録
    public void insert(Connection conn, Manager manager) throws SQLException {
        String sql = "INSERT INTO Manager (manager_id, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, manager.getManager_Id());  
            // Manager 객체에서 manager_id 값을 가져와 쿼리에 설정
            // Managerオブジェクトからmanager_idを取得してクエリに設定
            pstmt.setString(2, manager.getPassword());    
            // Manager 객체에서 password 값을 가져와 쿼리에 설정
            // Managerオブジェクトからpasswordを取得してクエリに設定
            pstmt.executeUpdate();  
            // 데이터베이스에 새로운 관리자 정보 삽입
            // データベースに新しい管理者情報を挿入
        }
    }
}
