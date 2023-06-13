package service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.StatusCode;
import dao.FeedbackDao;
import model.ApiResponse;
import model.entity.Feedback;
import model.request.FeedbackRequest;

@Path("feedback")
public class FeedbackService {
	private static final Logger logger = Logger.getLogger(FeedbackService.class.getName());

	@Inject
	private FeedbackDao<Feedback> feedbackDaoImpl;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response meetings() {
		logger.info("Get All Feedback");
		try {
			
			List<Feedback> feedbacks = feedbackDaoImpl.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), feedbacks))
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMeeting(FeedbackRequest feedbackRequest){
		try {
			Feedback feedback = new Feedback();
			feedback.setIdShareholder(feedbackRequest.getIdShareholder());
			feedback.setContent(feedbackRequest.getContent());
			int insert = feedbackDaoImpl.save(feedback);
			
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					feedback)).build();
		} catch (Exception e) {
			logger.error("ERROR INSERT : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_FAILED.getValue(),
					StatusCode.INSERT_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get-feedback-by-meeting/{idMeeting}")
	public Response getFeedbackByMeeting(@PathParam("idMeeting")Integer idMeeting) {
		try {
			List<Feedback> feedbacks = feedbackDaoImpl.getFeedbackByMeeting(idMeeting);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), feedbacks))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
}
