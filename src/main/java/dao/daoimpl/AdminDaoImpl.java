package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.ejb.Stateless;

import connection.ConnectionUtils;
import dao.AdminDao;
import model.entity.Admin;

@Stateless
public class AdminDaoImpl implements AdminDao<Admin> {
	
	Connection conn = null;

	@Override
	public Optional<Admin> findByUserNameAndPassword(String username, String password) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblAccountAdmin aa WHERE aa.username=? AND aa.password=?");
		PreparedStatement stmt=null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Admin admin =new Admin(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						"xxxxxxxxxxx");
				return Optional.of(admin);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Optional.empty();
	}

}
