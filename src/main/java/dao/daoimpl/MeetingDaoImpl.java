package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import connection.ConnectionUtils;
import dao.MeetingDao;
import model.entity.MeetingInfo;

@Stateless
public class MeetingDaoImpl implements MeetingDao<MeetingInfo> {
private static Logger logger=Logger.getLogger(MeetingDaoImpl.class.getName());
	
	Connection conn = null;
	@Override
	public List<MeetingInfo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MeetingInfo> getById(Integer id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tblMeeting WHERE id = ?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, id);
			//stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo();
				meetingInfo.setIdCompany(rs.getString("idCompany"));
				meetingInfo.setNameMeeting(rs.getString("nameMeeting"));
				meetingInfo.setNumberOrganized(rs.getInt("numberOrganized"));
				meetingInfo.setYearOrganized(rs.getDate("yearOrganized"));
				meetingInfo.setStatus(rs.getInt("status"));
				meetingInfo.setImageBanner(rs.getString("imageBanner"));
				meetingInfo.setStartTime(rs.getTimestamp("startTime"));
				meetingInfo.setEndTime(rs.getTimestamp("endTime"));
				meetingInfo.setAddress(rs.getString("address"));
				return Optional.of(meetingInfo);
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA BY ID: "+e.getMessage());
			return Optional.empty();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}				
		return Optional.empty();
	}

	@Override
	public Integer save(MeetingInfo meetingInfo) {
		StringBuilder sql = new StringBuilder(""
				+ "INSERT INTO tblMeeting (idCompany,nameMeeting,numberOrganized,yearOrganized,status,imageBanner,"
				+ "startTime,endTime,address) VALUES(?,?,?,?,?,?,?,?,?)");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, meetingInfo.getIdCompany());
			stmt.setString(2, meetingInfo.getNameMeeting());
			stmt.setInt(3, meetingInfo.getNumberOrganized());
			stmt.setDate(4, meetingInfo.getYearOrganized());
			stmt.setInt(5, meetingInfo.getStatus());
			stmt.setString(6, meetingInfo.getImageBanner());
			stmt.setTimestamp(7, meetingInfo.getStartTime());
			stmt.setTimestamp(8, meetingInfo.getEndTime());
			stmt.setString(9, meetingInfo.getAddress());
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
	public Integer update(MeetingInfo meetingInfo) {
		StringBuilder sql = new StringBuilder(""
				+ "UPDATE tblMeeting SET idCompany=?,nameMeeting=?,numberOrganized=?,yearOrganized=?,status=?,"
				+ "imageBanner=?,startTime=?,endTime=?,address=? WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, meetingInfo.getIdCompany());
			stmt.setString(2, meetingInfo.getNameMeeting());
			stmt.setInt(3, meetingInfo.getNumberOrganized());
			stmt.setDate(4, meetingInfo.getYearOrganized());
			stmt.setInt(5, meetingInfo.getStatus());
			stmt.setString(6, meetingInfo.getImageBanner());
			stmt.setTimestamp(7, meetingInfo.getStartTime());
			stmt.setTimestamp(8, meetingInfo.getEndTime());
			stmt.setString(9, meetingInfo.getAddress());
			stmt.setInt(10, meetingInfo.getId());
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
	public Integer delete(MeetingInfo meetingInfo) {
		StringBuilder sql = new StringBuilder("DELETE FROM tblMeeting  WHERE id=?");
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, meetingInfo.getId());
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
	public Optional<MeetingInfo> getById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer getStatusByIdMeeting(Integer idMeeting) {
		StringBuilder sql = new StringBuilder("SELECT status FROM tblMeeting WHERE id=? ");
		PreparedStatement stmt = null;
		Integer status=-1 ;
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, idMeeting);
			//stmt.executeQuery();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				status = rs.getInt("status");
			}
		} catch (Exception e) {
			logger.error("ERROR GET DATA BY ID: "+e.getMessage());
			return -1;
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return status;
	}

}
