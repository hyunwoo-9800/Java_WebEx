/**
 * 
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author develop
 * 
 * DB연동용 유틸리티 클래스
 */
public class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/WebEx_JSP");
		
	}
	
}
