package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public void uploadFile(ShareHolder shareHolderInfo) throws SQLException {
		
		StringBuilder sql = new StringBuilder(
				  "INSERT INTO tblShareholder "
				+ "(fullname,identityCard,email,address,phoneNumber,nationality,username,password,idMeeting,status,"
				+ "numberShares,numberSharesAuth,role)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		  int batchSize=20000;
		  int count =0;

		try {
			logger.info("UPLOAD EXCEL FILE TO DB");
			conn = ConnectionUtils.getInstance().getConnection();
			long start=System.currentTimeMillis();
			PreparedStatement stmt=null;
			for(int i=0;i<20000;i++) {
				stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, shareHolderInfo.getFullname()+i);
				stmt.setString(2, shareHolderInfo.getIdentityCard()+i);
				stmt.setString(3, shareHolderInfo.getEmail()+i);
				stmt.setString(4, shareHolderInfo.getAddress()+i);
				stmt.setString(5, shareHolderInfo.getPhoneNumber()+i);
				stmt.setString(6, shareHolderInfo.getNationality());
				stmt.setString(7, shareHolderInfo.getUsername()+i);
				stmt.setString(8, shareHolderInfo.getPassword());
				stmt.setInt(9, shareHolderInfo.getIdMeeting());
				stmt.setInt(10, shareHolderInfo.getStatus());
				stmt.setInt(11, shareHolderInfo.getNumberShares());
				stmt.setInt(12, shareHolderInfo.getNumberSharesAuth());
				stmt.setInt(13, shareHolderInfo.getRole());
				stmt.addBatch();
				count++;
				if(count%batchSize==0) {
					logger.info("Commit batch");
					int [] result=stmt.executeBatch();
					logger.info("Number rows: "+result.length);
					conn.commit();
				}
				stmt.executeBatch();
			}
			logger.info("TEST OK");
			long end = System.currentTimeMillis();
			logger.info("TIME INSERT TO DB: " + (end - start));
			
		} catch (Exception e) {
			logger.error("Transaction Failed: "+e.getMessage());
			
			//conn.rollback();
		}finally {
			try {
				conn.close();
			} catch (Exception e2) {
				logger.info(e2.getMessage());
			}
			
		}
		
	}

}
