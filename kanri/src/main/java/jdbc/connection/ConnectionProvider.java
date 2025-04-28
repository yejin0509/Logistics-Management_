package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider{
	
	public static Connection getConnection() throws java.sql.SQLException{
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:inventory");
	}
}