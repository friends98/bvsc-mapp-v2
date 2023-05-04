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
		StringBuilder sql = new StringBuilder("SELECT * FROM tblShareholder_Info si WHERE si.id=?");
		PreparedStatement stmt = null;
		ShareHolderInfo shareHoldersInfo=new ShareHolderInfo();
		try {
			logger.info("GET DATA FROM SHAREHOLDER_INFO TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			logger.info("ID"+id);

			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				shareHoldersInfo.setId(rs.getString("id"));
				shareHoldersInfo.setFullname(rs.getString("fullname"));
				shareHoldersInfo.setIdentityCard(rs.getString("identity_card"));
				shareHoldersInfo.setEmail(rs.getString("email"));
				shareHoldersInfo.setAddress(rs.getString("address"));
				shareHoldersInfo.setPhoneNumber(rs.getString("phone_number"));
				shareHoldersInfo.setNationality(rs.getString("nationality"));
			}
			
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		logger.info("Test"+shareHoldersInfo.getId());
		return Optional.of(shareHoldersInfo);
	}

	@Override
	public void save(ShareHolderInfo t) {
		// TODO Auto-generated method stub
		
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
