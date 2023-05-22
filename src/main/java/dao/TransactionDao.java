package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.AuthTransactionHistory;

@Stateless
public class TransactionDao {
	private static final Logger logger = Logger.getLogger(TransactionDao.class.getName());
	private Connection conn;
	
	
	
	@SuppressWarnings("resource")
	public Integer createTransactionShared(AuthTransactionHistory authTransactionHistory) {
		StringBuilder sqlFrom = new StringBuilder("UPDATE tblShareholder SET numberShares=numberShares-? WHERE id=? ");
		StringBuilder sqlTo = new StringBuilder("UPDATE tblShareholder SET numberSharesAuth=numberSharesAuth+? "
				+ "WHERE id=?");
		StringBuilder sqlTransHistory = new StringBuilder("INSERT INTO tblAuth_Transaction_History "
				+ "(idShareholder,idShareholderAuth,amount,createdAt,updatedAt) VALUES(?,?,?,?,?)");
		PreparedStatement stmt = null;
		int resultUpdateFrom,resultUpdateTo,resultInsertTrans=0;
	
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlFrom.toString());
			stmt.setInt(1,authTransactionHistory.getAmount());
			stmt.setString(2, authTransactionHistory.getIdShareholder());
			resultUpdateFrom=stmt.executeUpdate();
			
			stmt = conn.prepareStatement(sqlTo.toString());
			stmt.setInt(1, authTransactionHistory.getAmount());
			stmt.setString(2, authTransactionHistory.getIdShareholderAuth());
			resultUpdateTo=stmt.executeUpdate();
			
			if(resultUpdateFrom==0||resultUpdateTo==0) {
				conn.rollback();
				logger.info("Transaction Failed!");
				return 0;
			}
			stmt=conn.prepareStatement(sqlTransHistory.toString());
			stmt.setString(1, authTransactionHistory.getIdShareholder());
			stmt.setString(2, authTransactionHistory.getIdShareholderAuth());
			stmt.setInt(3, authTransactionHistory.getAmount());
			stmt.setDate(4, authTransactionHistory.getCreatedAt());
			stmt.setDate(resultUpdateTo, authTransactionHistory.getUpdatedAt());
			
			resultInsertTrans=stmt.executeUpdate();
			if(resultInsertTrans==0) {
				conn.rollback();
				logger.info("Insert transaction history failed!");
				return 0;
			}
			conn.commit();
			return 1;
			
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
