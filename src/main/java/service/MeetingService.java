package service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.Constants;
import common.StatusCode;
import dao.MeetingDao;
import dao.daoimpl.MeetingDaoImpl;
import model.ApiResponse;
import model.entity.MeetingInfo;
import model.request.MeetingInfoRequest;

@Path("meeting")
public class MeetingService {
	private static final Logger logger = Logger.getLogger(MeetingService.class.getName());
	
	@Inject
	private utils.FileUtils fileUtils;
	
	@Inject 
	private MeetingDao<MeetingInfo> meetingDaoImpl;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response meetingDetails(@PathParam("id")Integer id) {
		meetingDaoImpl = new MeetingDaoImpl();
		try {
			Optional<MeetingInfo> opMeetingInfo = meetingDaoImpl.getById(id);
			if(opMeetingInfo.isEmpty()) {
				return Response.ok(new ApiResponse(
						StatusCode.DATA_FAILED.getValue(),
						StatusCode.DATA_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(),
					opMeetingInfo.get())).build();
		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.DATA_FAILED.getValue(),
					StatusCode.DATA_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response meetings() {
		logger.info("Get All Shareholder");
		try {
			
			List<MeetingInfo> meetings = meetingDaoImpl.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), meetings))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}

	}
	
	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMeeting(MeetingInfoRequest meetingInfoReq){
//		String image64=fileUtils.uploadImage(meetingInfoReq.getImageBanner());

		try {
			MeetingInfo meetingInfo = new MeetingInfo();
			meetingInfo.setIdCompany(meetingInfoReq.getIdCompany());
			meetingInfo.setNameMeeting(meetingInfoReq.getNameMeeting());
			meetingInfo.setNumberOrganized(meetingInfoReq.getNumberOrganized());
			meetingInfo.setYearOrganized(meetingInfoReq.getYearOrganized());
			meetingInfo.setStatus(Constants.MEETING_INIT);
			meetingInfo.setImageBanner(meetingInfoReq.getImageBanner());
			meetingInfo.setStartTime(meetingInfoReq.getStartTime());
			meetingInfo.setEndTime(meetingInfoReq.getEndTime());
			meetingInfo.setAddress(meetingInfoReq.getAddress());
			int insert = meetingDaoImpl.save(meetingInfo);
			
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					meetingInfo)).build();
		} catch (Exception e) {
			logger.error("ERROR INSERT : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_FAILED.getValue(),
					StatusCode.INSERT_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response updateMeeting(@PathParam("id")Integer id,MeetingInfoRequest meetingInfoReq) {
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setId(id);
		meetingInfo.setIdCompany(meetingInfoReq.getIdCompany());
		meetingInfo.setNameMeeting(meetingInfoReq.getNameMeeting());
		meetingInfo.setNumberOrganized(meetingInfoReq.getNumberOrganized());
		meetingInfo.setYearOrganized(meetingInfoReq.getYearOrganized());
		meetingInfo.setStatus(meetingInfoReq.getStatus());
		meetingInfo.setImageBanner(meetingInfoReq.getImageBanner());
		meetingInfo.setStartTime(meetingInfoReq.getStartTime());
		meetingInfo.setEndTime(meetingInfoReq.getStartTime());
		meetingInfo.setAddress(meetingInfoReq.getAddress());
		try {
			int edit = meetingDaoImpl.update(meetingInfo);
			if(edit==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					meetingInfo)).build();
		} catch (Exception e) {
			logger.error("ERROR INSERT : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteMeeting(@PathParam("id")Integer id) {
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setId(id);
		try {
			int delete = meetingDaoImpl.delete(meetingInfo);
			if(delete==0) {
				return Response.ok(new ApiResponse(
						StatusCode.DELETE_FAILED.getValue(),
						StatusCode.DELETE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DELETE_SUCCESS.getValue(),
					StatusCode.DELETE_SUCCESS.getDescription(),
					null)).build();
		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.DELETE_FAILED.getValue(),
					StatusCode.DELETE_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allByCompany/{idCompany}")
	public Response getAllByCompany(@PathParam("idCompany")String idCompany) {
		logger.info("Get All Meeting");
		try {
			
			List<MeetingInfo> meetingInfos = meetingDaoImpl.getByIdCompany(idCompany);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), meetingInfos))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
//	@POST
//    @Path("/upload-image")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadImage(@MultipartForm FormData formData) {
//        try {
//            Response response = meetingDaoImpl.uploadImage(formData.getImageFile());
//            return response;
//        } catch (Exception e) {
//            // Xử lý exception nếu cần thiết
//            return Response.serverError().build();
//        }
//    }

}
