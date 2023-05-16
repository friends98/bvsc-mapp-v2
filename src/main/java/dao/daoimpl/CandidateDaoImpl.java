package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import dao.CandidateDao;
import model.entity.Candidate;
@Stateless
public class CandidateDaoImpl implements CandidateDao<Candidate> {
	private static final Logger logger = Logger.getLogger(CandidateDaoImpl.class.getName());
	
	Connection conn;
	@Override
	public List<Candidate> getAll() {
		List<Candidate> candidates = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblCandidate");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE CANDIDATE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(rs.getInt("id"));
				candidate.setIdElection(rs.getString("idElection"));
				candidate.setFullName(rs.getString("fullname"));
				candidate.setBirthday(rs.getDate("birthday"));
				candidate.setAddress(rs.getString("address"));
				candidate.setSummaryInfo(rs.getString("summaryInfo"));
				candidates.add(candidate);
				
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return candidates;
	}

	@Override
	public Optional<Candidate> getById(String id) {
		
		StringBuilder sql = new StringBuilder("SELECT * FROM tblCandidate c WHERE c.id=?");
		PreparedStatement stmt = null;
		Candidate candidate = new Candidate();
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				candidate.setId(rs.getInt("id"));
				candidate.setIdElection(rs.getString("idElection"));
				candidate.setFullName(rs.getString("fullname"));
				candidate.setBirthday(rs.getDate("birthday"));
				candidate.setAddress(rs.getString("address"));
				candidate.setSummaryInfo(rs.getString("summaryInfo"));
				return Optional.of(candidate);
			}
			return Optional.empty();
		} catch (Exception e) {
			logger.error("ERROR GET DATA BY ID: "+e.getMessage());
			return Optional.empty();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
	}

	@Override
	public Integer save(Candidate t) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO tblCandidate (idMeeting,fullname,birthday,address,summaryInfo)" 
			  + "VALUES(?,?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, t.getIdElection());
			stmt.setString(2, t.getFullName());
			stmt.setDate(3, t.getBirthday());
			stmt.setString(4, t.getAddress());
			stmt.setString(5, t.getSummaryInfo());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
		} catch (Exception e) {
			logger.error("ERROR INSERT DATA : "+e.getMessage());
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

	@Override
	public Integer update(Candidate candidate) {
		StringBuilder sql = new StringBuilder(
				"UPDATE tblCandidate SET idMeeting=?, fullname=?, birthday=?, address=?,summaryInfo=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA CANDIDATE TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, candidate.getIdElection());
			stmt.setString(2, candidate.getFullName());
			stmt.setDate(3, candidate.getBirthday());
			stmt.setString(4, candidate.getAddress());
			stmt.setString(5, candidate.getSummaryInfo());
			stmt.setInt(6, candidate.getId());
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
				return 0;
			}
		}
		
	}

	@Override
	public Integer delete(Candidate t) {
		StringBuilder sql = new StringBuilder("DELETE FROM tblCandidate  WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, t.getId());
			int rowDel = stmt.executeUpdate();
			if(rowDel>0) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			logger.error("ERROR DELETE DATA BY ID: "+e.getMessage());
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

	@Override
	public List<Candidate> findAllCandidateByIdMeeting(String idMeeting) {
		List<Candidate> candidates = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblCandidate WHERE idElection=?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE CANDIDATE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idMeeting);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(rs.getInt("id"));
				candidate.setIdElection("idElection");
				candidate.setFullName(rs.getString("fullname"));
				candidate.setBirthday(rs.getDate("birthday"));
				candidate.setAddress(rs.getString("address"));
				candidate.setSummaryInfo(rs.getString("summaryInfo"));
				candidates.add(candidate);
				
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return candidates;
	}

}
