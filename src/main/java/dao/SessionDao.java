package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.Session;

@Stateless
public class SessionDao {
	private static Logger logger=Logger.getLogger(SessionDao.class.getName());
	Connection conn ;
	
	public Integer insertSession(Session session) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblSession(idShareholder,startTime,ipAddress,deviceType) VALUES(?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT TABLE SESSION");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, session.getIdShareholder());
			stmt.setTimestamp(2, session.getStartTime());
			stmt.setString(3, session.getIpAddress());
			stmt.setString(4, session.getDeviceType());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
			
		} catch (Exception e) {
			logger.error("ERROR INSERT DATA: "+e.getMessage());
			return 0;
			
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}	
		
	}
	public Integer updateSession(Session session) {
		StringBuilder sql = new StringBuilder("UPDATE tblSession SET endTime = ? WHERE sesionId = ?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE TABLE SESSION");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setTimestamp(1, session.getEndTime());
			stmt.setString(2, session.getSessionId());
			logger.info(session.getEndTime());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
			
		} catch (Exception e) {
			logger.error("ERROR UPDATE DATA: "+e.getMessage());
			return 0;
			
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}	
	}

}
