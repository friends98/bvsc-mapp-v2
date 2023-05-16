package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.ResultElection;
import model.entity.ResultVoting;

@Stateless
public class MeetingResultDao {
	
	private static final Logger logger = Logger.getLogger(MeetingResultDao.class.getName());
	Connection conn;
	
	
	public Integer saveShareHolderVoting(List<ResultVoting> resultVotings) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblResult_Voting (idVoting,idShareholder,status) "
				+ "VALUES(?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA RESULT VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultVotings.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultVoting resultVoting = resultVotings.get(i);
				stmt.setString(1, resultVoting.getIdVoting());
				stmt.setString(2, resultVoting.getIdShareholder());
				stmt.setInt(3, resultVoting.getStatus());
				stmt.addBatch();
				stmt.executeBatch();
			}
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
				return 0;
			}
		}
	}
	public Integer saveShareHolderElection(List<ResultElection> resultElections) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblResult_Election (idElection,idShareholder,"
				+ "numberSharesForCandidate) VALUES (?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA RESULT ELECTION TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultElections.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultElection resultElection = resultElections.get(i);
				stmt.setString(1, resultElection.getIdElection());
				stmt.setString(2, resultElection.getIdShareholder());
				stmt.setInt(3, resultElection.getNumberShareCandidate());
				stmt.addBatch();
			}
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
				return 0;
			}
		}
	}
	public Integer updateShareHolderVoting(List<ResultVoting> resultVotings) {
		StringBuilder sql = new StringBuilder("UPDATE  tblResult_Voting SET status=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA RESULT VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultVotings.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultVoting resultVoting = resultVotings.get(i);
				stmt.setInt(1, resultVoting.getStatus());
				stmt.setString(2, resultVoting.getId());
				stmt.addBatch();
				stmt.executeBatch();
			}
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
				return 0;
			}
		}
	}
	public Integer updateShareHolderElection(List<ResultElection> resultElections) {
		StringBuilder sql = new StringBuilder("UPDATE  tblResult_Election SET numberSharesForCandidate=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA RESULT ELECTION TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultElections.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultElection resultElection = resultElections.get(i);
				stmt.setInt(1, resultElection.getNumberShareCandidate());
				stmt.setString(2, resultElection.getId());
				stmt.addBatch();
				stmt.executeBatch();
			}
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
				return 0;
			}
		}
	}
	
	
	
	
	
}
