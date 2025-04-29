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
    private ClientOrderDao outbound_Dao = new ClientOrderDao();

    public void saveOutboundList(List<OutBound> list) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            //DAO 호출 -> insert
            for (OutBound outbound : list) {
                outbound_Dao.insert(conn, outbound);
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    public List<Product> getAllProducts() throws SQLException {
        return ProductDao.getAllProducts();  // 정적 메서드니까 바로 사용 가능
    }

}

