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
import dao.ElectionDao;
import model.entity.Election;

@Stateless
public class ElectionDaoImpl implements ElectionDao<Election> {
	private static final Logger logger = Logger.getLogger(ElectionDaoImpl.class.getName());
	
	Connection conn;
	
	@Override
	public List<Election> getAll() {
		List<Election> elections = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblElection");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE ELECTION");
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Election election = new Election();
				election.setId(rs.getString("id"));
				election.setIdMeeting(rs.getInt("idMeeting"));
				election.setTitle(rs.getString("title"));
				election.setDescription(rs.getString("description"));
				election.setCreateTime(rs.getTimestamp("createdTime"));
				election.setModifiTime(rs.getTimestamp("modifiedTime"));
				elections.add(election);
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
		return elections;

	}

	@Override
	public Optional<Election> getById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblElection e WHERE e.id=?");
		PreparedStatement stmt = null;
		Election election = new Election();
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				election.setId(rs.getString("id"));
				election.setIdMeeting(rs.getInt("idMeeting"));
				election.setTitle(rs.getString("title"));
				election.setDescription(rs.getString("description"));
				election.setCreateTime(rs.getTimestamp("createdTime"));
				election.setModifiTime(rs.getTimestamp("modifiedTime"));
				return Optional.of(election);

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
	public Integer save(Election election) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO tblElection (idMeeting,title,description,createdTime)" 
			  + "VALUES(?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setInt(1, election.getIdMeeting());
			stmt.setString(2, election.getTitle());
			stmt.setString(3, election.getDescription());
			stmt.setTimestamp(4, election.getCreateTime());
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
	public Integer update(Election election) {
		StringBuilder sql = new StringBuilder(
				"UPDATE tblElection SET idMeeting=?, title=?, description=?, modifiedTime=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("UPDATE DATA ELECTION TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, election.getIdMeeting());
			stmt.setString(2, election.getTitle());
			stmt.setString(3, election.getDescription());
			stmt.setTimestamp(4, election.getModifiTime());
			stmt.setString(5, election.getId());
			stmt.executeUpdate();
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
	public Integer delete(Election t) {
		StringBuilder sql = new StringBuilder("DELETE FROM tblElection  WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, t.getId());
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

}
