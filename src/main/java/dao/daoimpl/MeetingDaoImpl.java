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
import dao.MeetingDao;
import model.entity.MeetingInfo;

@Stateless
public class MeetingDaoImpl implements MeetingDao<MeetingInfo> {
private static Logger logger=Logger.getLogger(MeetingDaoImpl.class.getName());
	Connection conn = null;
	@Override
	public List<MeetingInfo> getAll() {

		List<MeetingInfo> meetings = new ArrayList<MeetingInfo>();
		PreparedStatement stmt = null;
		try {
			logger.info("GET ALL DATA FROM MEETING TABLE");
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM tblMeeting");
			ResultSet rs =stmt.executeQuery();

			while (rs.next()) {
				MeetingInfo meeting = new MeetingInfo();
				meeting.setId(rs.getInt("id"));
				meeting.setIdCompany(rs.getInt("idCompany"));
				meeting.setNameMeeting(rs.getString("nameMeeting"));
				meeting.setNumberOrganized(rs.getInt("numberOrganized"));
				meeting.setYearOrganized(rs.getDate("yearOrganized"));
				meeting.setStatus(rs.getInt("status"));
				meeting.setImageBanner(rs.getString("imageBanner"));
				meeting.setStartTime(rs.getDate("startTime"));
				meeting.setEndTime(rs.getDate("endTime"));
				meeting.setAddress(rs.getString("address"));
				meetings.add(meeting);
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
		return meetings;
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
				meetingInfo.setId(id);
				meetingInfo.setIdCompany(rs.getInt("idCompany"));
				meetingInfo.setNameMeeting(rs.getString("nameMeeting"));
				meetingInfo.setNumberOrganized(rs.getInt("numberOrganized"));
				meetingInfo.setYearOrganized(rs.getDate("yearOrganized"));
				meetingInfo.setStatus(rs.getInt("status"));
				meetingInfo.setImageBanner(rs.getString("imageBanner"));
				meetingInfo.setStartTime(rs.getDate("startTime"));
				meetingInfo.setEndTime(rs.getDate("endTime"));
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
//		String imageBytes = meetingInfo.getImageBanner();
//		byte[] bytes = Base64.getDecoder().decode(imageBytes); 
//		String imageBase64 = Base64.getEncoder().encodeToString(bytes);
		try {
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, meetingInfo.getIdCompany());
			stmt.setString(2, meetingInfo.getNameMeeting());
			stmt.setInt(3, meetingInfo.getNumberOrganized());
			stmt.setDate(4, meetingInfo.getYearOrganized());
			stmt.setInt(5, meetingInfo.getStatus());
			stmt.setString(6, meetingInfo.getImageBanner());
			stmt.setDate(7, meetingInfo.getStartTime());
			stmt.setDate(8, meetingInfo.getEndTime());
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
			stmt.setInt(1, meetingInfo.getIdCompany());
			stmt.setString(2, meetingInfo.getNameMeeting());
			stmt.setInt(3, meetingInfo.getNumberOrganized());
			stmt.setDate(4, meetingInfo.getYearOrganized());
			stmt.setInt(5, meetingInfo.getStatus());
			stmt.setString(6, meetingInfo.getImageBanner());
			stmt.setDate(7, meetingInfo.getStartTime());
			stmt.setDate(8, meetingInfo.getEndTime());
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
	public List<MeetingInfo> getByIdCompany(String idCompany) {
		List<MeetingInfo> meetingInfos = new ArrayList<MeetingInfo>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblMeeting WHERE idCompany=?");
		PreparedStatement stmt = null;
		try {
			logger.info("GET DATA FROM Meeting TABLE");
			logger.info("SHARE HOLDER INFO ID: "+idCompany);
			conn = ConnectionUtils.getInstance().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, idCompany);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				MeetingInfo meetingInfo = new MeetingInfo();
				meetingInfo.setId(rs.getInt("id"));
				meetingInfo.setIdCompany(rs.getInt("idCompany"));
				meetingInfo.setNameMeeting(rs.getString("nameMeeting"));
				meetingInfo.setNumberOrganized(rs.getInt("numberOrganized"));
				meetingInfo.setYearOrganized(rs.getDate("yearOrganized"));
				meetingInfo.setStatus(rs.getInt("status"));
				meetingInfo.setImageBanner(rs.getString("imageBanner"));
				meetingInfo.setStartTime(rs.getDate("startTime"));
				meetingInfo.setEndTime(rs.getDate("endTime"));
				meetingInfo.setAddress(rs.getString("address"));
				meetingInfos.add(meetingInfo);
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
		return  meetingInfos;
	}
	
//    private static final String UPLOAD_FOLDER = "/bvsc-mapp-v2/resource/images"; // Thay đổi đường dẫn đến thư mục upload
//
//	@Override
//	public ResponseEntity<String> uploadImage(MultipartFile imageFile) {
//		try {
//            // Tạo đường dẫn tới thư mục upload
//            File uploadDir = new File(UPLOAD_FOLDER);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//
//            // Lưu file vào thư mục upload
//            String fileName = imageFile.getOriginalFilename();
//            Path filePath = Path.of(UPLOAD_FOLDER, fileName);
//            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//            return ResponseEntity.ok("Image uploaded successfully");
//        } catch (IOException e) {
//            // Xử lý exception nếu có lỗi trong quá trình xử lý ảnh
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//        }
//	}

}
