/**
 * 
 */
package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author develop
 * JDBC 연결 및 종료 유틸리티 클래스
 */
public class JdbcUtil {

	public static void close(ResultSet rs) {
		
		if (rs != null) {
			
			try {
				
				rs.close();
				
			} catch (SQLException ex) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	public static void close(Statement stmt) {
		
		if (stmt != null) {
			
			try {
				
				stmt.close();
				
			} catch (SQLException ex) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	public static void close(Connection conn) {
		
		if (conn != null) {
			
			try {
				
				conn.close();
				
			} catch (SQLException ex) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	public static void rollback(Connection conn) {
		
		if (conn != null) {
			
			try {
				
				conn.rollback();
				
			} catch (SQLException ex) {
				// TODO: handle exception
			}
			
		}
		
	}
	
}
