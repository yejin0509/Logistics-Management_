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
    private ManagerOrderDao inboundDao = new ManagerOrderDao();

    public void saveOutboundList(List<InBound> list) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            for (InBound outbound : list) {
            	inboundDao.insert(conn, outbound);
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

