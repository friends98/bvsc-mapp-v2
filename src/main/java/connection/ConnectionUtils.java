/**
 * 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

/**
 * @author it-admin
 *
 */
@Stateless
public class ConnectionUtils {
	private static final Logger logger=Logger.getLogger(ConnectionUtils.class);
	private static final String serverName="localhost";
	private static final String portNumber="1433";
	private static final String databaseName="Bvsc_App";
	private static final String username="sa";
	private static final String password="123456";

	private static final String url="jdbc:sqlserver://"+serverName+":"+portNumber+";database="+databaseName+";user="+username+";password="+password;
	Connection conn=null;
	public  Connection getConnection() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection(url);
			logger.info("DB connect successfully!");
		} catch (Exception e) {
			if(conn==null) {
				logger.info("DB connect is failed,null poiter exception");
			}
			logger.error("Error:"+e.getMessage());
			System.out.println(e.getMessage());
		

		}
		return conn;
	}
	public static void main(String[] args) {
		ConnectionUtils conn=new ConnectionUtils();
		System.out.println(conn.toString());
	}
	
}
