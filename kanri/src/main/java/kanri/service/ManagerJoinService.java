package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kanri.dao.ManagerDao;
import kanri.model.Manager;
import member.service.DuplicateIdException;

public class ManagerJoinService {

	private ManagerDao managerDao = new ManagerDao();

	public void join(ManagerJoinRequest joinRequest) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// ID 중복 검사
			Manager existing = managerDao.selectById(conn, joinRequest.getManager_Id());
			if (existing != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			// 관리자 객체 생성 후 저장
			Manager newManager = new Manager(
				joinRequest.getManager_Id(),
				joinRequest.getPassword()
			);

			managerDao.insert(conn, newManager);
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
