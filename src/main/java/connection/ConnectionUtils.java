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
	// singleton connection
	private static ConnectionUtils instance = null;
	private Connection conn = null;

	// define info DB
	private static final Logger logger = Logger.getLogger(ConnectionUtils.class);
	private static final String serverName = "localhost";
	private static final String portNumber = "1433";
	private static final String databaseName = "BVSC_App_Voting1";
	private static final String username = "sa";
	private static final String password = "123456";

	private static final String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";database=" + databaseName
			+ ";user=" + username + ";password=" + password;
	public ConnectionUtils() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			logger.info("DB connect successfully!");
		} catch (Exception e) {
			if (conn == null) {
				logger.info("DB connect is failed,null poiter exception");
			}
			logger.error("Error:" + e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public  static ConnectionUtils getInstance() throws SQLException {
		if (instance == null) {
			instance=new ConnectionUtils();
		}else if(instance.getConnection().isClosed()) {
			instance=new ConnectionUtils();
			return instance;
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

}
