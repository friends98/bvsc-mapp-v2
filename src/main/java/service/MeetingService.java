package service;

import java.sql.Timestamp;
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

import org.apache.commons.io.FileUtils;
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
	private MeetingDao<MeetingInfo> meetingDaoImpl;
	
	@Inject
	private utils.FileUtils fileUtils;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMeeting(MeetingInfoRequest meetingInfoReq) {
		String image64=fileUtils.uploadImage(meetingInfoReq.getImageBanner());
		//logger.info("image :"+image64);
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setIdCompany(meetingInfoReq.getIdCompany());
		meetingInfo.setNameMeeting(meetingInfoReq.getNameMeeting());
		meetingInfo.setNumberOrganized(meetingInfoReq.getNumberOrganized());
		meetingInfo.setYearOrganized(meetingInfoReq.getYearOrganized());
		meetingInfo.setStatus(Constants.MEETING_INIT);
		meetingInfo.setImageBanner(image64);
		meetingInfo.setStartTime(Timestamp.valueOf(meetingInfoReq.getStartTime()));
		meetingInfo.setEndTime(Timestamp.valueOf(meetingInfoReq.getEndTime()));
		meetingInfo.setAddress(meetingInfoReq.getAddress());
		try {
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
	@Path("edit/{id}")
	public Response updateMeeting(@PathParam("id")Integer id,MeetingInfoRequest meetingInfoReq) {
		String image64=fileUtils.uploadImage(meetingInfoReq.getImageBanner());
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setId(id);
		meetingInfo.setIdCompany(meetingInfoReq.getIdCompany());
		meetingInfo.setNameMeeting(meetingInfoReq.getNameMeeting());
		meetingInfo.setNumberOrganized(meetingInfoReq.getNumberOrganized());
		meetingInfo.setYearOrganized(meetingInfoReq.getYearOrganized());
		meetingInfo.setStatus(meetingInfoReq.getStatus());
		meetingInfo.setImageBanner(image64);
		meetingInfo.setStartTime(Timestamp.valueOf(meetingInfoReq.getStartTime()));
		meetingInfo.setEndTime(Timestamp.valueOf(meetingInfoReq.getStartTime()));
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
	@Path("{id}")
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

}
