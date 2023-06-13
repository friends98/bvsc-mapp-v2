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
import dao.FeedbackDao;
import model.entity.Feedback;

@Stateless
public class FeedbackDaoImpl implements FeedbackDao<Feedback>{
	private static Logger logger = Logger.getLogger(MeetingDaoImpl.class.getName());
	Connection conn = null;
	
	@Override
	public List<Feedback> getAll() {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM MEETING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblFeedback");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Feedback feedback = new Feedback();
				feedback.setId(rs.getInt("id"));
				feedback.setIdShareholder(rs.getString("idShareholder"));
				feedback.setContent(rs.getString("content"));
				feedback.setTimeFeedback(rs.getTimestamp("timeFeedback"));
				feedbacks.add(feedback);
			}

		} catch (Exception e) {
			logger.error("ERROR GET DATA : " + e.getMessage());

		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}

		}
		return feedbacks;
	}

	@Override
	public Optional<Feedback> getById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer save(Feedback feedback) {
		StringBuilder sql = new StringBuilder(
				"" + "INSERT INTO tblFeedback (idShareholder,content) VALUES(?,?)");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, feedback.getIdShareholder());
			stmt.setString(2, feedback.getContent());
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
		} catch (Exception e) {
			logger.error("ERROR INSERT DATA FEEDBACK : " + e.getMessage());
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
	public Integer update(Feedback t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Feedback t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> getFeedbackByMeeting(Integer idMeeting) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		StringBuilder sql = new StringBuilder("SELECT fb.* FROM tblFeedback fb JOIN tblShareholder s ON fb.idShareholder = s.id WHERE s.idMeeting = ? ORDER BY timeFeedback ASC");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM FEEDBACK TABLE");
			logger.info("IDMEETING INFO ID: "+idMeeting);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, idMeeting);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Feedback feedback = new Feedback();
				feedback.setId(rs.getInt("id"));
				feedback.setIdShareholder(rs.getString("idShareholder"));
				feedback.setContent(rs.getString("content"));
				feedback.setTimeFeedback(rs.getTimestamp("timeFeedback"));

				feedbacks.add(feedback);
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
		return  feedbacks;
	}

}
