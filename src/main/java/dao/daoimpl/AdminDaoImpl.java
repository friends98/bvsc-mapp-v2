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
import dao.AdminDao;
import model.entity.Admin;

@Stateless
public class AdminDaoImpl implements AdminDao<Admin> {
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class.getName());

	Connection conn = null;

	@Override
	public Optional<Admin> findByUserNameAndPassword(String username, String password) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblAccountAdmin aa WHERE aa.username=? AND aa.password=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin(rs.getString(1), rs.getString(2), "xxxxxxxxxxx", rs.getInt(4));
				return Optional.of(admin);
			}
		} catch (Exception e) {
			logger.error("ERROR SELECT DATA: " + e.getMessage());
		} finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.warn(e2.getMessage());
			}
		}
		return Optional.empty();
	}

	@Override
	public List<Admin> getAll() {
		List<Admin> admins = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblAccountAdmin");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA TABLE VOTING");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4));
				admins.add(admin);
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
		return admins;
	}

	@Override
	public Optional<Admin> getById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer save(Admin admin) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblAccountAdmin (username,password,role)" + "VALUES(?,?,?)");
		PreparedStatement stmt = null;
		try {
			logger.info("INSERT DATA VOTING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.setInt(3, admin.getRole());
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
	public Integer update(Admin t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Admin t) {
		// TODO Auto-generated method stub
		return null;
	}

}
