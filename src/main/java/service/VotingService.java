package service;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response voting(VotingRequest votingReq) {
		votingImpl = new VotingDaoImpl();
		// check request
		try {
			Voting voting = new Voting();
			voting.setIdMeeting(votingReq.getIdMeeting());
			voting.setContent(votingReq.getContent());
			voting.setCreatedTime(new Timestamp(System.currentTimeMillis()));
					
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

}
