package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

	private static final Logger logger = Logger.getLogger(VotingDaoImpl.class);

	Connection conn = null;

	@Override
	public List<Voting> getAll() {
		List<Voting> list = new ArrayList<>();
		list.add(new Voting());
		return list;
	}

	@Override
	public Optional<Voting> getById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void save(Voting voting) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO tblVoting (id_meeting,content,created_time,modified_time)" + "VALUES(?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			//stmt.setString(1, voting.getId());
			stmt.setString(1, voting.getIdMeeting());
			stmt.setString(2, voting.getContent());
			stmt.setTimestamp(3, voting.getCreatedTime());
			stmt.setTimestamp(4, voting.getModifiedTime());
			stmt.addBatch();

			//execute
			stmt.executeBatch();
			ResultSet rs=stmt.getGeneratedKeys();
			logger.info("GENERATE KEY: "+rs.getString(1));

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			
			try {

				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		


	}

	@Override
	public void update(Voting t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Voting t) {
		// TODO Auto-generated method stub

	}

}
