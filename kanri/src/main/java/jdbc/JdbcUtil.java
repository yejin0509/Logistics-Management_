package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	// ResultSet rs 종료 메서드
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();

			} catch (SQLException ex) {

			}
		}
	}

	//Statement stmt 종료 메서드
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();

			} catch (SQLException ex) {

			}
		}
	}

	//Connection conn 종료 메서드
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();

			} catch (SQLException ex) {

			}
		}
	}

	// Connection conn 롤백 메서드
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();

			} catch (SQLException ex) {

			}
		}
	}

}
