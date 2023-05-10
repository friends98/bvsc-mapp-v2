package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import dao.VotingDao;
import model.entity.Voting;

@Stateless
public class VotingDaoImpl implements VotingDao<Voting> {

	private static final Logger logger = Logger.getLogger(VotingDaoImpl.class.getName());

	Connection conn = null;

	@Override
	public List<Voting> getAll() {
		List<Voting> votings=new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblVoting");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE VOTING");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Voting voting = new Voting(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4),
						rs.getTimestamp(5));
				votings.add(voting);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return votings;
	}

	@Override
	public Optional<Voting> getById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblVoting v WHERE v.id=?");
		PreparedStatement stmt = null;
		Voting voting =new Voting();
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				voting.setId(rs.getString(1));
				voting.setIdMeeting(id);
				voting.setContent(rs.getString(3));
				voting.setCreatedTime(rs.getTimestamp(4));
				voting.setModifiedTime(rs.getTimestamp(5));
				return Optional.of(voting);
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
	public Integer save(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO tblVoting (idMeeting,content,createdTime)" + "VALUES(?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			//stmt.setString(1, voting.getId());
			stmt.setString(1, voting.getIdMeeting());
			stmt.setString(2, voting.getContent());
			stmt.setTimestamp(3, voting.getCreatedTime());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		} finally {
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
	public Integer update(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"UPDATE tblVoting SET content=?, modifiedTime=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, voting.getContent());
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.setString(3, voting.getId());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		} finally {
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
	public Integer delete(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"DELETE FROM tblVoting v WHERE v.id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, voting.getId());
			int rowDelete=stmt.executeUpdate();
			if(rowDelete>0) {
				return 1;
			}
			return 0;
			
		} catch (Exception e) {
			logger.error("ERROR DELETE DATA : "+e.getMessage());
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
