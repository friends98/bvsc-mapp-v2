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
						rs.getInt(2),
						rs.getString(3),
						rs.getDate(4),
						rs.getDate(5),
						rs.getInt(6));
				votings.add(voting);
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
		return votings;
	}

	@Override
	public Optional<Voting> getById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblVoting WHERE id = ?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			//stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Voting voting = new Voting();
				voting.setId(rs.getString("id"));
				voting.setIdMeeting(rs.getInt("idMeeting"));
				voting.setContent(rs.getString("content"));
				voting.setCreatedTime(rs.getDate("createdTime"));
				voting.setModifiedTime(rs.getDate("modifiedTime"));
				voting.setStatus(rs.getInt("status"));
				return Optional.of(voting);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA BY ID: "+e.getMessage());
			return Optional.empty();
		}finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}				
		return Optional.empty();
	}

	@Override
	public Integer save(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO tblVoting (idMeeting,content,createdTime,status)" + "VALUES(?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			//stmt.setString(1, voting.getId());
			stmt.setInt(1, voting.getIdMeeting());
			stmt.setString(2, voting.getContent());
			stmt.setDate(3, voting.getCreatedTime());
			stmt.setInt(4, voting.getStatus());
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
		StringBuilder sql = new StringBuilder("UPDATE tblVoting SET idMeeting=?, content=?,createdTime=?, modifiedTime=?, status=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, voting.getIdMeeting());
			stmt.setString(2, voting.getContent());
			stmt.setDate(3,voting.getCreatedTime());
			stmt.setDate(4,voting.getModifiedTime());
			stmt.setInt(5,voting.getStatus());
			stmt.setString(6,voting.getId());
			
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
		} catch (Exception e) {
			logger.error("ERROR UPDATE DATA : "+e.getMessage());
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
	public Integer delete(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"DELETE FROM tblVoting WHERE id=?");
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

	@Override
	public List<Voting> getByIdMeeting(String idMeeting) {
		List<Voting> votings = new ArrayList<Voting>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblVoting WHERE idMeeting=? ORDER BY content ASC");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Voting TABLE");
			logger.info("Voting INFO ID: "+idMeeting);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idMeeting);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Voting voting = new Voting();
				voting.setId(rs.getString("id"));
				voting.setIdMeeting(rs.getInt("idMeeting"));
				voting.setContent(rs.getString("content"));
				voting.setCreatedTime(rs.getDate("createdTime"));
				voting.setModifiedTime(rs.getDate("modifiedTime"));
				voting.setStatus(rs.getInt("status"));
				votings.add(voting);
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
		return  votings;
	}
}
