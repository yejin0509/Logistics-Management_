package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ManagerOrderDao;
import kanri.dao.ProductDao;
import kanri.model.InBound;
import kanri.model.Product;

public class InboundService {
    private ManagerOrderDao inbound_Dao = new ManagerOrderDao(); // 인바운드 DAO 객체 생성
    // インバウンドDAOオブジェクトの生成

    // 인바운드 리스트 저장 (DB에 인서트)
    // インバウンドリストの保存（DBに挿入）
    public void saveOutboundList(List<InBound> list) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // DB 연결
            conn.setAutoCommit(false); // 트랜잭션 수동 커밋 모드
            // DBに接続、トランザクションの自動コミットを無効にする

            //DAO 호출 -> insert (각각의 인바운드 객체를 DB에 삽입)
            // DAO呼び出し -> 挿入（各インバウンドオブジェクトをDBに挿入）
            for (InBound outbound : list) {
                inbound_Dao.insert(conn, outbound); // 각 인바운드를 DB에 삽입
                // 各インバウンドをDBに挿入
            }

            conn.commit(); // 트랜잭션 커밋
            // トランザクションのコミット
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // 오류 발생 시 롤백
            throw e; // 예외를 다시 던짐
            // エラーが発生した場合、ロールバックし、例外を再度投げる
        } finally {
            if (conn != null) conn.close(); // 연결 종료
            // 接続終了
        }
    }
    
    // 모든 상품 목록을 반환
    // すべての製品リストを返す
    public List<Product> getAllProducts() throws SQLException {
        return ProductDao.getAllProducts(); // ProductDao에서 모든 상품을 조회
        // ProductDaoからすべての製品を取得
    }

}
