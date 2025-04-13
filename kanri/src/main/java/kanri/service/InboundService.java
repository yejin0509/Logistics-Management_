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
    private ManagerOrderDao inbound_Dao = new ManagerOrderDao();

    public void saveOutboundList(List<InBound> list) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            //DAO 호출 -> insert
            for (InBound outbound : list) {
            	inbound_Dao.insert(conn, outbound);
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
        return ProductDao.getAllProducts();
    }

}

