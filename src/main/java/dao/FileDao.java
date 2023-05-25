package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.ShareHolder;

// upload file to DB
@Stateless
public class FileDao {
	private static final Logger logger = Logger.getLogger(FileDao.class);

	Connection conn = null;

	@SuppressWarnings("resource")
	public int upload(List<ShareHolder> list, Integer idMeeting) throws SQLException {

		StringBuilder sql = new StringBuilder("INSERT INTO tblShareholder "
				+ "(fullname,shareHolderCode,identityCard,email,address,phoneNumber,nationality,username,password,idMeeting,status,"
				+ "numberShares,numberSharesAuth,role)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		int count = 0;
		PreparedStatement stmt = null;

		try {
			logger.info("UPLOAD EXCEL FILE TO DB");
			conn = ConnectionUtils.getInstance().getConnection();
			long start = System.currentTimeMillis();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql.toString());
			int size = list.size();
			logger.info("Size: " + size);
			for (int i = 0; i < size; i++) {
				ShareHolder shareHolder = list.get(i);
				stmt.setString(1, shareHolder.getFullname());
				stmt.setString(2, shareHolder.getShareHolderCode());
				stmt.setString(3, shareHolder.getIdentityCard());
				stmt.setString(4, shareHolder.getEmail());
				stmt.setString(5, shareHolder.getAddress());
				stmt.setString(6, shareHolder.getPhoneNumber());
				stmt.setString(7, shareHolder.getNationality());
				stmt.setString(8, shareHolder.getUsername());
				stmt.setString(9, "xxxxxxx");
				stmt.setInt(10, idMeeting);
				stmt.setInt(11, 0);
				stmt.setInt(12, shareHolder.getNumberShares());
				stmt.setInt(13, shareHolder.getNumberSharesAuth());
				stmt.setInt(14, shareHolder.getRole());
				stmt.addBatch();
				if (count == 2000) {
					count = 0;
					stmt.executeBatch();
					stmt.clearBatch();
					conn.commit();
				}
				count++;
			}
			long end = System.currentTimeMillis();
			logger.info("TIME INSERT TO DB: " + (end - start));
			return 1;
		} catch (Exception e) {
			logger.error("Transaction Failed: " + e.getMessage());
			conn.rollback();
			return 0;
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.info(e2.getMessage());
			}
		}
	}
}