package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.AuthTransactionHistory;
import java.util.ArrayList;
import java.sql.ResultSet;


@Stateless
public class TransactionDao {
	private static final Logger logger = Logger.getLogger(TransactionDao.class.getName());
	private Connection conn;
	
	public List<AuthTransactionHistory> getAll() {
		List<AuthTransactionHistory> authTransactionHistories=new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblAuth_Transaction_History");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE Transaction history");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				AuthTransactionHistory authTransactionHistory = new AuthTransactionHistory(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getDate(6));
				authTransactionHistories.add(authTransactionHistory);
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
		return authTransactionHistories;
	}
	
	public Integer createTransactionShared(AuthTransactionHistory authTransactionHistory) {
		
		StringBuilder sqlTransHistory = new StringBuilder("INSERT INTO tblAuth_Transaction_History "
				+ "(idShareholder,idShareholderAuth,amount) VALUES(?,?,?)");
		PreparedStatement stmt = null;
	
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			stmt=conn.prepareStatement(sqlTransHistory.toString());
			stmt.setString(1, authTransactionHistory.getIdShareholder());
			stmt.setString(2, authTransactionHistory.getIdShareholderAuth());
			stmt.setInt(3, authTransactionHistory.getAmount());
			//stmt.addBatch();
			int resultInsertTrans=stmt.executeUpdate();
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