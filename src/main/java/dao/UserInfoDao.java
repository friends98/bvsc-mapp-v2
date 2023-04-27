package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import model.entity.UserInfo;

@Stateless
public class UserInfoDao implements CommonDao<UserInfo>,AuthDao<UserInfo>{
	private static final Logger logger=Logger.getLogger(UserInfoDao.class);
	
	Connection conn=null;
	List<UserInfo> userInfos=new ArrayList<>();
	@Override
	public List<UserInfo> getAll() {
		
		return null;
	}
	
	public List<UserInfo> searchByIdentityCard(String identityCard){
		return null;
	}

	@Override
	public Optional<UserInfo> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void save(UserInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserInfo t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Optional<UserInfo> findByUserNameAndPassword(String username,String password) {
		StringBuilder sql=new StringBuilder("SELECT * FROM tblUser_Info ui WHERE ui.username=? AND ui.password=?");
		PreparedStatement stmt=null;
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				logger.info("ok"+rs.getString(2));
				UserInfo userInfo=new UserInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9));
				return Optional.of(userInfo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.warn(e2.getMessage());
			}
		}
		return null;
	}
}
