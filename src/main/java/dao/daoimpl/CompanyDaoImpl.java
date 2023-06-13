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
import dao.CompanyDao;
import model.entity.CompanyInfo;

@Stateless
public class CompanyDaoImpl implements CompanyDao<CompanyInfo>{
	private static final Logger logger = Logger.getLogger(ShareHolderDaoImpl.class.getName());

	Connection conn = null;
	@Override
	public List<CompanyInfo> getAll() {

		List<CompanyInfo> companys = new ArrayList<CompanyInfo>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM MEETING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblCompanyInfo");
			ResultSet rs =stmt.executeQuery();

			while (rs.next()) {
				CompanyInfo companyInfo = new CompanyInfo();
				companyInfo.setId(rs.getInt("id"));
				companyInfo.setCompanyName(rs.getString("companyName"));
				companyInfo.setStockCode(rs.getString("stockCode"));
				companyInfo.setTaxCode(rs.getInt("taxCode"));
				companyInfo.setAddress(rs.getString("address"));
				companyInfo.setFoundedYear(rs.getTimestamp("foundedYear"));
				companys.add(companyInfo);
			}
			
		} catch (Exception e) {
			logger.error("ERROR GET DATA : "+e.getMessage());
			
		}finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
			
		}
		return companys;
	}

	@Override
	public Optional<CompanyInfo> getById(Integer id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblCompanyInfo WHERE id = ?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			//stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CompanyInfo companyInfo = new CompanyInfo();
				companyInfo.setId(rs.getInt("id"));
				companyInfo.setCompanyName(rs.getString("companyName"));
				companyInfo.setStockCode(rs.getString("stockCode"));
				companyInfo.setTaxCode(rs.getInt("taxCode"));
				companyInfo.setAddress(rs.getString("address"));
				companyInfo.setFoundedYear(rs.getTimestamp("foundedYear"));
				return Optional.of(companyInfo);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA BY ID: "+e.getMessage());
			return Optional.empty();
		}finally {
			try {
				stmt.close();
//				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}				
		return Optional.empty();
	}

	@Override
	public Integer save(CompanyInfo companyInfo) {
		StringBuilder sql = new StringBuilder("INSERT INTO tblCompanyInfo (companyName,stockCode,taxCode,address,foundedYear) VALUES(?,?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, companyInfo.getCompanyName());
			stmt.setString(2, companyInfo.getStockCode());
			stmt.setInt(3, companyInfo.getTaxCode());
			stmt.setString(4, companyInfo.getAddress());
			stmt.setTimestamp(5, companyInfo.getFoundedYear());
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
	public Integer update(CompanyInfo companyInfo) {
		StringBuilder sql = new StringBuilder(""
				+ "UPDATE tblCompanyInfo SET companyName=?,stockCode=?,taxCode=?,address=?,foundedYear=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, companyInfo.getCompanyName());
			stmt.setString(2, companyInfo.getStockCode());
			stmt.setInt(3, companyInfo.getTaxCode());
			stmt.setString(4, companyInfo.getAddress());
			stmt.setTimestamp(5, companyInfo.getFoundedYear());
			stmt.setInt(6, companyInfo.getId());
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
	public Integer delete(CompanyInfo companyInfo) {
		StringBuilder sql = new StringBuilder("DELETE FROM tblCompanyInfo  WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, companyInfo.getId());
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
	public Optional<CompanyInfo> getById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



}
