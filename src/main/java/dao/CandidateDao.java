package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import connection.ConnectionUtils;
import model.entity.CompanyInfo;

@Stateless
public class CandidateDao {
	Connection conn=null;
	public List<CompanyInfo> getAllCandidate(){
		List<CompanyInfo> listCandidate=new ArrayList<>();
		String sql="SELECT * FROM tblCompany";
		PreparedStatement ps=null;
		try {
			conn=ConnectionUtils.getInstance().getConnection();
			 ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CompanyInfo candidate = new CompanyInfo(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4),rs.getString(5),
						new Date(rs.getDate(6).getTime()));
				listCandidate.add(candidate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return listCandidate;

	}
	
	

}
