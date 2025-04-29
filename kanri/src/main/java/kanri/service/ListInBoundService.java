package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.InBoundDao;
import kanri.model.InBound;

public class ListInBoundService {

		private InBoundDao inBoundDao = new InBoundDao();
		private int size = 10;
		
		public InBoundPage getInBoundPage(int pageNum) {
			
			int firstRow = 0;
			int endRow = 0;
			List<InBound>content = null;
			try(Connection conn = ConnectionProvider.getConnection()){
				int total = inBoundDao.selectCount(conn);
				if(total > 0) {
					firstRow = (pageNum -1)*size +1;
					endRow = firstRow + size -1;
					content = inBoundDao.select(conn);
				}
				return new InBoundPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
}
}