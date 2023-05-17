package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import common.Constants;
import connection.ConnectionUtils;
import model.entity.ShareHolder;




// upload file to DB
@Stateless
public class FileDao {
	private static final Logger logger = Logger.getLogger(FileDao.class);
	
	Connection conn = null;
	@SuppressWarnings("resource")
	public void uploadFile(ShareHolder shareHolder) throws SQLException {
		
		StringBuilder sql = new StringBuilder(
				  "INSERT INTO tblShareholder "
				+ "(fullname,identityCard,email,address,phoneNumber,nationality,username,password,idMeeting,status,"
				+ "numberShares,numberSharesAuth,role,shareHolderCode)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		  int batchSize=2000;
		  int count =0;

		try {
			logger.info("UPLOAD EXCEL FILE TO DB");
			conn = ConnectionUtils.getInstance().getConnection();
			long start=System.currentTimeMillis();
			conn.setAutoCommit(true);
			PreparedStatement stmt=null;
			for(int i=0;i<20000;i++) {
				stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, shareHolder.getFullname());
				stmt.setString(2, shareHolder.getIdentityCard()+i);
				stmt.setString(3, shareHolder.getEmail()+i);
				stmt.setString(4, shareHolder.getAddress());
				stmt.setString(5, shareHolder.getPhoneNumber()+i);
				stmt.setString(6, shareHolder.getNationality());
				stmt.setString(7, shareHolder.getUsername()+i);
				stmt.setString(8, shareHolder.getPassword());
				stmt.setInt(9, 1);
				stmt.setInt(10,shareHolder.getStatus());
				stmt.setInt(11,shareHolder.getNumberShares());
				stmt.setInt(12,shareHolder.getNumberSharesAuth());
				stmt.setInt(13, shareHolder.getRole());
				stmt.setString(14,shareHolder.getShareHolderCode());
				stmt.addBatch();
				count++;
				if(count%batchSize==0) {
					stmt.clearBatch();
					conn.commit();
				}
			}
			stmt.executeBatch();
			long end = System.currentTimeMillis();
			logger.info("TIME INSERT TO DB: " + (end - start));
			
		} catch (Exception e) {
			logger.error("Transaction Failed: "+e.getMessage());
			conn.rollback();
			//conn.rollback();
		}finally {
			try {
				conn.close();
			} catch (Exception e2) {
				logger.info(e2.getMessage());
			}
			
		}
		
	}
	
	
	public int upload(List<ShareHolder> list) throws SQLException {
		
		StringBuilder sql = new StringBuilder(
				  "INSERT INTO tblShareholder "
				+ "(fullname,identityCard,email,address,phoneNumber,nationality,username,password,idMeeting,status,"
				+ "numberShares,numberSharesAuth,role,shareHolderCode)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		  int batchSize=Constants.BATCH_SIZE;
		  int count =0;
		  PreparedStatement stmt=null;

		try {
			logger.info("UPLOAD EXCEL FILE TO DB");
			conn = ConnectionUtils.getInstance().getConnection();
			long start=System.currentTimeMillis();
			conn.setAutoCommit(false);

			int size = list.size();
			logger.info("Size: "+size);
			for(int i=0;i<size;i++) {
				stmt = conn.prepareStatement(sql.toString());

				ShareHolder shareHolder=list.get(i);
				stmt.setString(1, shareHolder.getFullname());
				stmt.setString(2, shareHolder.getIdentityCard());
				stmt.setString(3, shareHolder.getEmail());
				stmt.setString(4, shareHolder.getAddress());
				stmt.setString(5, shareHolder.getPhoneNumber());
				stmt.setString(6, shareHolder.getNationality());
				stmt.setString(7, shareHolder.getUsername());
				stmt.setString(8, "xxxxxxx");
				stmt.setInt(9, 1);
				stmt.setInt(10,0);
				stmt.setInt(11,shareHolder.getNumberShares());
				stmt.setInt(12,shareHolder.getNumberSharesAuth());
				stmt.setInt(13, shareHolder.getRole());
				stmt.setString(14,shareHolder.getShareHolderCode());
				stmt.addBatch();
				count++;
				if(count%2==0) {
					stmt.executeBatch();
					conn.commit();
				}
				if(count%2==1) {
					stmt.executeBatch();
					conn.commit();
				}
			}
			//stmt.executeBatch();

			conn.commit();

			long end = System.currentTimeMillis();
			logger.info("TIME INSERT TO DB: " + (end - start));
			return 1;
		} catch (Exception e) {
			logger.error("Transaction Failed: "+e.getMessage());
			conn.rollback();
			return 0;
		}finally {
			try {
				stmt.close();
				conn.close();


			} catch (Exception e2) {
				logger.info(e2.getMessage());
			}
		}
	}
}
