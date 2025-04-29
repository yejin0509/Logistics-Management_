package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientDao;
import kanri.model.Client;

public class ClientJoinService {

	private ClientDao clientDao = new ClientDao();

	public void join(ClientJoinRequest joinRequest) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 아이디 중복 검사
			Client existingclient = clientDao.selectById(conn, joinRequest.getClient_Id());
			if (existingclient != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			// 새로운 client 객체 생성
			Client newclient = new Client(
				joinRequest.getClient_Id(),
				joinRequest.getPassword(),
				joinRequest.getCompany(),
				joinRequest.getC_Number(),
				joinRequest.getAddress()
			);

			clientDao.insertClient(conn, newclient);
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
