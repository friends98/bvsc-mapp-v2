package service;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.Constants;
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
	public Response addVoting(VotingRequest votingReq) {
		logger.info("INSERT DATA TO TABLE VOTING");
		votingImpl = new VotingDaoImpl();
		// check request
		if (votingReq.getId().isBlank() || votingReq.getIdMeeting().isBlank() || votingReq.getContent().isBlank()) {
			return Response.ok(new ApiResponse(
					Constants.HTTP_CODE_400, 
					Constants.REQUEST_INVALID_MESS,
					new Object())).build();
		}
		Voting voting = new Voting(votingReq.getId(),
				votingReq.getIdMeeting(),
				votingReq.getContent(),
				votingReq.getCreatedTime(),
				votingReq.getModifiedTime());
		votingImpl.save(voting);
		return Response.ok(new ApiResponse(StatusCode.INSERT_SUCCESS.getValue(),
				StatusCode.INSERT_SUCCESS.getDescription(), voting)).build();
	}

}
