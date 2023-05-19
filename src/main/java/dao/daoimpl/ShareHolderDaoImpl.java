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
import dao.ShareHolderDao;
import model.entity.ShareHolder;


@Stateless
public class ShareHolderDaoImpl implements ShareHolderDao<ShareHolder>{
	
	private static final Logger logger = Logger.getLogger(ShareHolderDaoImpl.class.getName());

	Connection conn=null;
	
	@Override
	public List<ShareHolder> getAll() {
		
		List<ShareHolder> shareHolders = new ArrayList<ShareHolder>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM SHAREHOLDER TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblShareholder");
			ResultSet rs =stmt.executeQuery();

			while (rs.next()) {
				ShareHolder shareHolder = new ShareHolder();
				shareHolder.setId(rs.getString("id"));
				shareHolder.setFullname(rs.getString("fullname"));
				shareHolder.setShareHolderCode(rs.getString("shareHolderCode"));
				shareHolder.setIdentityCard(rs.getString("identityCard"));
				shareHolder.setEmail(rs.getString("email"));
				shareHolder.setAddress(rs.getString("address"));
				shareHolder.setPhoneNumber(rs.getString("phoneNumber"));
				shareHolder.setNationality(rs.getString("nationality"));
				shareHolder.setUsername(rs.getString("username"));
				shareHolder.setPassword(rs.getString("password"));
				shareHolder.setIdMeeting(rs.getInt("idMeeting"));
				shareHolder.setStatus(rs.getInt("status"));
				shareHolder.setNumberShares(rs.getInt("numberShares"));
				shareHolder.setNumberSharesAuth(rs.getInt("numberSharesAuth"));
				shareHolder.setRole(rs.getInt("role"));
				shareHolders.add(shareHolder);
			}
			
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
			
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
			
		}
		return shareHolders;
	}

	@Override
	public Optional<ShareHolder> getById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblShareholder WHERE id=?");
		PreparedStatement stmt = null;
		ShareHolder shareHolder = new ShareHolder();
		try {
			logger.info("GET DATA FROM SHAREHOLDER TABLE");
			logger.info("SHARE HOLDER INFO ID: "+id);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				shareHolder.setId(rs.getString("id"));
				shareHolder.setFullname(rs.getString("fullname"));
				shareHolder.setShareHolderCode(rs.getString("shareHolderCode"));
				shareHolder.setIdentityCard(rs.getString("identityCard"));
				shareHolder.setEmail(rs.getString("email"));
				shareHolder.setPhoneNumber(rs.getString("phoneNumber"));
				shareHolder.setAddress(rs.getString("address"));
				shareHolder.setNationality(rs.getString("nationality"));
				shareHolder.setUsername(rs.getString("username"));
				shareHolder.setPassword(rs.getString("password"));
				shareHolder.setIdMeeting(rs.getInt("idMeeting"));
				shareHolder.setStatus(rs.getInt("status"));
				shareHolder.setNumberShares(rs.getInt("numberShares"));
				shareHolder.setNumberSharesAuth(rs.getInt("numberSharesAuth"));
				shareHolder.setRole(rs.getInt("role"));
				
				return Optional.of(shareHolder);
			}

		} catch (Exception e) {
			logger.error("ERROR GET DATA :"+e.getMessage());
			return Optional.empty();

		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return  Optional.empty();
	}

	@Override
	public Integer save(ShareHolder shareHolder) {
		StringBuilder sql = new StringBuilder(
				    "INSERT INTO tblShareholder (fullname,shareHolderCode,identityCard,email,address,phoneNumber,nationality,username,"
				  + "password,idMeeting,status,numberShares,numberSharesAuth,role) "
				  + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1, shareHolder.getFullname());
			stmt.setString(2,shareHolder.getShareHolderCode());
			stmt.setString(3, shareHolder.getIdentityCard());
			stmt.setString(4, shareHolder.getEmail());
			stmt.setString(5, shareHolder.getAddress());
			stmt.setString(6, shareHolder.getPhoneNumber());
			stmt.setString(7, shareHolder.getNationality());
			stmt.setString(8, shareHolder.getUsername());
			stmt.setString(9, shareHolder.getPassword());
			stmt.setInt(10, shareHolder.getIdMeeting());
			stmt.setInt(11,shareHolder.getStatus());
			stmt.setInt(12,shareHolder.getNumberShares());
			stmt.setInt(13,shareHolder.getNumberSharesAuth());
			stmt.setInt(14, shareHolder.getRole());
		
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
			
		} catch (Exception e) {
			logger.error("ERROR INSERT DATA : "+e.getMessage());
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

	@Override
	public Integer update(ShareHolder shareHolder) {
		StringBuilder sql = new StringBuilder(""
				+ "UPDATE tblShareholder SET fullname=?,shareHolderCode=?,identityCard=?,email=?,address=?,"
				+ "phoneNumber=?,nationality=?,username=?,password=?,idMeeting=?,status=?,numberShares=?,"
				+ "numberSharesAuth=?,role=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, shareHolder.getFullname());
			stmt.setString(2,shareHolder.getShareHolderCode());
			stmt.setString(3, shareHolder.getIdentityCard());
			stmt.setString(4, shareHolder.getEmail());
			stmt.setString(5, shareHolder.getAddress());
			stmt.setString(6, shareHolder.getPhoneNumber());
			stmt.setString(7, shareHolder.getNationality());
			stmt.setString(8, shareHolder.getUsername());
			stmt.setString(9, shareHolder.getPassword());
			stmt.setInt(10, shareHolder.getIdMeeting());
			stmt.setInt(11,shareHolder.getStatus());
			stmt.setInt(12,shareHolder.getNumberShares());
			stmt.setInt(13,shareHolder.getNumberSharesAuth());
			stmt.setInt(14, shareHolder.getRole());
			stmt.setString(15,shareHolder.getId());
			
			stmt.addBatch();
			stmt.executeBatch();
			return 1;
		} catch (Exception e) {
			logger.error("ERROR UPDATE DATA : "+e.getMessage());
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

	@Override
	public Integer delete(ShareHolder shareHolder) {
		StringBuilder sql = new StringBuilder("DELETE FROM tblShareholder WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, shareHolder.getId());
			int rowDel=stmt.executeUpdate();
			if(rowDel>0) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			logger.error("ERROR DELETE DATA BY ID: "+e.getMessage());
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

	@Override
	public List<ShareHolder> getByIdMeeting(String idMeeting) {
		List<ShareHolder> shareHolders = new ArrayList<ShareHolder>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblShareholder WHERE idMeeting=?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM SHAREHOLDER TABLE");
			logger.info("SHARE HOLDER INFO ID: "+idMeeting);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idMeeting);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				ShareHolder shareHolder = new ShareHolder();
				shareHolder.setId(rs.getString("id"));
				shareHolder.setFullname(rs.getString("fullname"));
				shareHolder.setShareHolderCode(rs.getString("shareHolderCode"));
				shareHolder.setIdentityCard(rs.getString("identityCard"));
				shareHolder.setEmail(rs.getString("email"));
				shareHolder.setAddress(rs.getString("address"));
				shareHolder.setPhoneNumber(rs.getString("phoneNumber"));
				shareHolder.setNationality(rs.getString("nationality"));
				shareHolder.setUsername(rs.getString("username"));
				shareHolder.setPassword(rs.getString("password"));
				shareHolder.setIdMeeting(rs.getInt("idMeeting"));
				shareHolder.setStatus(rs.getInt("status"));
				shareHolder.setNumberShares(rs.getInt("numberShares"));
				shareHolder.setNumberSharesAuth(rs.getInt("numberSharesAuth"));
				shareHolder.setRole(rs.getInt("role"));
				shareHolders.add(shareHolder);
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
		return  shareHolders;
	}
	
	
	@Override
	public Optional<ShareHolder> findByUserNameAndPassword(String username, String password) {
		StringBuilder sql=new StringBuilder("SELECT * FROM tblShareholder sh WHERE sh.username=? AND sh.password=?");
		PreparedStatement stmt=null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ShareHolder shareholder = new ShareHolder(
						rs.getString("id"),
						rs.getString("fullname"),
						rs.getString("shareHolderCode"),
						rs.getString("identityCard"),
						rs.getString("email"),
						rs.getString("address"),
						rs.getString("phoneNumber"),
						rs.getString("nationality"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getInt("idMeeting"),
						rs.getInt("status"),
						rs.getInt("numberShares"),
						rs.getInt("numberSharesAuth"),
						rs.getInt("role"));
				return Optional.of(shareholder);
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
		return Optional.empty();
	}

}
