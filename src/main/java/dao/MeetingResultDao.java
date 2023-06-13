package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public List<ResultVoting> getResultVotingByShareholder(String idShareholder) {
		List<ResultVoting> resultVotings = new ArrayList<ResultVoting>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblResult_Voting WHERE idShareholder=?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Meeting TABLE");
			logger.info("SHARE HOLDER INFO ID: "+idShareholder);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idShareholder);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				ResultVoting resultVoting = new ResultVoting();
				resultVoting.setId(rs.getInt("id"));
				resultVoting.setIdVoting(rs.getString("idVoting"));
				resultVoting.setIdShareholder(rs.getString("idShareholder"));
				resultVoting.setStatus(rs.getInt("status"));
				
				resultVotings.add(resultVoting);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return  resultVotings;
	}
	
	public List<ResultVoting> getResultVotingByMeeting(Integer idMeeting) {
		List<ResultVoting> resultVotings = new ArrayList<ResultVoting>();
		StringBuilder sql = new StringBuilder("SELECT rv.* FROM tblResult_Voting rv JOIN tblVoting v ON rv.idVoting = v.id WHERE v.idMeeting = ?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Meeting TABLE");
			logger.info("IDMEETING INFO ID: "+idMeeting);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, idMeeting);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				ResultVoting resultVoting = new ResultVoting();
				resultVoting.setId(rs.getInt("id"));
				resultVoting.setIdVoting(rs.getString("idVoting"));
				resultVoting.setIdShareholder(rs.getString("idShareholder"));
				resultVoting.setStatus(rs.getInt("status"));
				
				resultVotings.add(resultVoting);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return  resultVotings;
	}
	
	public List<ResultVoting> getAllResultVoting() {

		List<ResultVoting> resultVotings = new ArrayList<ResultVoting>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM MEETING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblResult_Voting");
			ResultSet rs =stmt.executeQuery();

			while (rs.next()) {
				ResultVoting resultVoting = new ResultVoting();
				resultVoting.setId(rs.getInt("id"));
				resultVoting.setIdVoting(rs.getString("idVoting"));
				resultVoting.setIdShareholder(rs.getString("idShareholder"));
				resultVoting.setStatus(rs.getInt("status"));
				resultVotings.add(resultVoting);
			}
			
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
			
		}finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
			
		}
		return resultVotings;
	}
	
	public Integer saveShareHolderElection(List<ResultElection> resultElections) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblResult_Election (idCandidate,idShareholder,"
				+ "numberSharesForCandidate) VALUES (?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA RESULT ELECTION TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultElections.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultElection resultElection = resultElections.get(i);
				stmt.setString(1, resultElection.getIdCandidate());
				stmt.setString(2, resultElection.getIdShareholder());
				stmt.setInt(3, resultElection.getNumberSharesForCandidate());
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
	public Integer updateShareHolderVoting(List<ResultVoting> resultVotings) {
		StringBuilder sql = new StringBuilder("UPDATE tblResult_Voting SET status=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA RESULT VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultVotings.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultVoting resultVoting = resultVotings.get(i);
				stmt.setInt(1, resultVoting.getStatus());
				stmt.setInt(2, resultVoting.getId());
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
	
	public List<ResultElection> getResultElectionByMeeting(Integer idMeeting) {
		List<ResultElection> resultElections = new ArrayList<ResultElection>();
		StringBuilder sql = new StringBuilder("SELECT re.* FROM tblResult_Election re JOIN tblCandidate c ON re.idCandidate = c.id JOIN tblElection e ON c.idElection = e.id WHERE e.idMeeting = ?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Meeting TABLE");
			logger.info("IDMEETING INFO ID: "+idMeeting);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, idMeeting);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				ResultElection resultElection = new ResultElection();
				resultElection.setId(rs.getInt("id"));
				resultElection.setIdCandidate(rs.getString("idCandidate"));
				resultElection.setIdShareholder(rs.getString("idShareholder"));
				resultElection.setNumberSharesForCandidate(rs.getInt("numberSharesForCandidate"));
				resultElection.setTimeElection(rs.getTimestamp("timeElection"));

				resultElections.add(resultElection);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return  resultElections;
	}
	
	public Integer updateShareHolderElection(List<ResultElection> resultElections) {
		StringBuilder sql = new StringBuilder("UPDATE tblResult_Election SET numberSharesForCandidate=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA RESULT ELECTION TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			int size = resultElections.size();
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());
				ResultElection resultElection = resultElections.get(i);
				stmt.setInt(1, resultElection.getNumberSharesForCandidate());
				stmt.setInt(2, resultElection.getId());
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
	
	public List<ResultElection> getResultElectionByShareholder(String idShareholder) {
		List<ResultElection> resultElections = new ArrayList<ResultElection>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblResult_Election WHERE idShareholder=?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Election TABLE");
			logger.info("SHARE HOLDER INFO ID: "+idShareholder);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idShareholder);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				ResultElection resultElection = new ResultElection();
				resultElection.setId(rs.getInt("id"));
				resultElection.setIdCandidate(rs.getString("idCandidate"));
				resultElection.setIdShareholder(rs.getString("idShareholder"));
				resultElection.setNumberSharesForCandidate(rs.getInt("numberSharesForCandidate"));
				
				resultElections.add(resultElection);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return  resultElections;
	}
	
	public List<ResultElection> getAllResultElection() {

		List<ResultElection> resultElections = new ArrayList<ResultElection>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM ResultElection TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblResult_Election");
			ResultSet rs =stmt.executeQuery();

			while (rs.next()) {
				ResultElection resultElection = new ResultElection();
				resultElection.setId(rs.getInt("id"));
				resultElection.setIdCandidate(rs.getString("idCandidate"));
				resultElection.setIdShareholder(rs.getString("idShareholder"));
				resultElection.setNumberSharesForCandidate(rs.getInt("numberSharesForCandidate"));
				resultElections.add(resultElection);
			}
			
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
			
		}finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
			
		}
		return resultElections;
	}
	
	
	
	
}
