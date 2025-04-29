package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.OutBoundDao;
import kanri.model.OutBound;

public class ListOutBoundService {

		private OutBoundDao outBoundDao = new OutBoundDao();
		private int size = 10;
		
		public OutBoundPage getOutBoundPage(int pageNum) {
			
			int firstRow = 0;
			int endRow = 0;
			List<OutBound>content = null;
			try(Connection conn = ConnectionProvider.getConnection()){
				int total = outBoundDao.selectCount(conn);
				if(total > 0) {
					firstRow = (pageNum -1)*size +1;
					endRow = firstRow + size -1;
					content = outBoundDao.select(conn);
				}
				return new OutBoundPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
}
}