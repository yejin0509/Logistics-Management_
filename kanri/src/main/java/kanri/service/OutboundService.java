package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientOrderDao;
import kanri.dao.ProductDao;
import kanri.model.OutBound;
import kanri.model.Product;

public class OutboundService {
    private ClientOrderDao outbound_Dao = new ClientOrderDao(); // ClientOrderDao 객체 생성 / ClientOrderDaoオブジェクトを作成

    // Outbound 리스트 저장
    public void saveOutboundList(List<OutBound> list) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false); // 자동 커밋 끄기 / 自動コミットをオフにする

            // DAO 호출 -> insert
            // DAOを呼び出し -> insert
            for (OutBound outbound : list) {
                outbound_Dao.insert(conn, outbound); // 각 OutBound 객체를 데이터베이스에 삽입 / 各OutBoundオブジェクトをデータベースに挿入
            }

            conn.commit(); // 커밋 / コミット
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // 예외 발생 시 롤백 / 例外が発生した場合、ロールバック
            throw e;
        } finally {
            if (conn != null) conn.close(); // 연결 종료 / 接続を閉じる
        }
    }

    // 모든 제품을 가져오기
    public List<Product> getAllProducts() throws SQLException {
        return ProductDao.getAllProducts();  // 정적 메서드니까 바로 사용 가능 / 静的メソッドなので直接使用可能
    }
}
