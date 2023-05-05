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
import model.entity.ShareHolderInfo;

@Stateless
public class ShareHolderDaoImpl implements ShareHolderDao<ShareHolderInfo>{
	
	private static final Logger logger = Logger.getLogger(ShareHolderDaoImpl.class.getName());

	Connection conn=null;
	List<ShareHolderInfo> shareHolderInfo=new ArrayList<>();

	@Override
	public List<ShareHolderInfo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShareHolderInfo> getById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblShareholder_Info WHERE id=?");
		PreparedStatement stmt = null;
		ShareHolderInfo shareHoldersInfo = new ShareHolderInfo();
		try {
			logger.info("GET DATA FROM SHAREHOLDER TABLE");
			logger.info("SHARE HOLDER INFO ID: "+id);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				shareHoldersInfo.setId(rs.getString(1));
				shareHoldersInfo.setFullname(rs.getString(2));
				shareHoldersInfo.setIdentityCard(rs.getString(3));
				shareHoldersInfo.setEmail(rs.getString(4));
				shareHoldersInfo.setAddress(rs.getString(5));
				shareHoldersInfo.setAddress(rs.getString(6));
				shareHoldersInfo.setNationality(rs.getString(7));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return Optional.of(shareHoldersInfo);
	}

	@Override
	public void save(ShareHolderInfo shareHolderInfo) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblShareholder_Info (");
		PreparedStatement stmt = null;
		
	}

	@Override
	public void update(ShareHolderInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShareHolderInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShareHolderInfo> searchByIdentityCard(String identityCard) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
