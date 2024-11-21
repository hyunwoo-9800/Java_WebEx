/**
 * 
 */
package jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author develop
 *
 */
public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	private void loadJDBCDriver() {
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException ex) {
			// TODO: handle exception
			
			throw new RuntimeException("fail to load JDBC Driver", ex);
			
		}
		
	}
	
	private void initConnectionPool() {
		
		try {
			
			String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres?" + "useUnicode=true&characterEncoding=utf8";
			String userName = "postgres";
			String pw = "dev";
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, userName, pw);
			
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnectionFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, poolConfig);
			poolableConnectionFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp");
			driver.registerPool("postgres", connectionPool);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	
}
