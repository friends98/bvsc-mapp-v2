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

import common.StatusCode;
import dao.VotingDao;
import dao.daoimpl.VotingDaoImpl;
import model.ApiResponse;
import model.entity.Voting;
import model.request.VotingRequest;

@Path("voting")
public class VotingService {
	private static final Logger logger = Logger.getLogger(VotingService.class); 
	
	@Inject
	private VotingDao<Voting> votingImpl;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response votings() {
		logger.info("Get All Voting");
		try {
			
			List<Voting> votings = votingImpl.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), votings))
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
	public Response voting(VotingRequest votingReq) {
		votingImpl = new VotingDaoImpl();
		// check request
		try {
			Voting voting = new Voting();
			voting.setIdMeeting(votingReq.getIdMeeting());
			voting.setContent(votingReq.getContent());
			voting.setCreatedTime(votingReq.getCreatedTime());
					
			int insert = votingImpl.save(voting);
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					voting)).build();

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
	public Response updateVoting(@PathParam("id")String id,VotingRequest votingReq) {
		votingImpl = new VotingDaoImpl();
		try {
			Voting voting = new Voting();
			voting.setIdMeeting(votingReq.getIdMeeting());
			voting.setContent(votingReq.getContent());
			voting.setCreatedTime(votingReq.getCreatedTime());
			voting.setModifiedTime(votingReq.getModifiedTime());
			voting.setId(id);
			
			int edit = votingImpl.update(voting);
			if(edit==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					voting)).build();
		} catch (Exception e) {
			logger.error("ERROR UPDATE : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response votingDetail(@PathParam("id")String id) {
		votingImpl = new VotingDaoImpl();
		try {
			Optional<Voting> opVoting = votingImpl.getById(id);
			if(opVoting.isEmpty()) {
				return Response.ok(new ApiResponse(
						StatusCode.DATA_FAILED.getValue(),
						StatusCode.DATA_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(),
					opVoting.get())).build();
		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.DATA_FAILED.getValue(),
					StatusCode.DATA_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response votingDelete(@PathParam("id") String id) {
		votingImpl = new VotingDaoImpl();
		try {
			Voting voting = new Voting();
			voting.setId(id);
			int delete = votingImpl.delete(voting);
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
	@Path("/allByMeeting/{idMeeting}")
	public Response getAllByMeetingVoting(@PathParam("idMeeting")String idMeeting) {
		logger.info("Get All Voting By Meetings");
		try {
			
			List<Voting> votings = votingImpl.getByIdMeeting(idMeeting);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), votings))
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
