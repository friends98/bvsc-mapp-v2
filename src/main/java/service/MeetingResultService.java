package service;

import java.util.List;

import javax.inject.Inject;
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
import dao.MeetingResultDao;
import model.ApiResponse;
import model.entity.ResultElection;
import model.entity.ResultVoting;

@Path("result")
public class MeetingResultService {
	
	private static final Logger logger = Logger.getLogger(MeetingResultService.class.getName());
	
	@Inject
	private MeetingResultDao meetingResultDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("save-voting")
	public Response saveResultVoting(List<ResultVoting>resultVotings) {
		try {
			int save =meetingResultDao.saveShareHolderVoting(resultVotings);
			if(save==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					resultVotings)).build();
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
	@Path("get-result-voting-by-shareholder/{idShareholder}")
	public Response getResultByShareholder(@PathParam("idShareholder")String idShareholder) {
		try {
			
			List<ResultVoting> resultVotings = meetingResultDao.getResultVotingByShareholder(idShareholder);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultVotings))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get-result-voting-by-meeting/{idMeeting}")
	public Response getResultVotingByMeeting(@PathParam("idMeeting")Integer idMeeting) {
		try {
			
			List<ResultVoting> resultVotings = meetingResultDao.getResultVotingByMeeting(idMeeting);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultVotings))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all-result-voting")
	public Response getAllResultVoting() {
		logger.info("Get All Result Voting");
		try {
			
			List<ResultVoting> resultVotings = meetingResultDao.getAllResultVoting();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultVotings))
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
	@Path("save-election")
	public Response saveResultElection(List<ResultElection> resultElections) {
		try {
			int save = meetingResultDao.saveShareHolderElection(resultElections);
			if(save==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					resultElections)).build();
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
	@Path("get-result-election-by-shareholder/{idShareholder}")
	public Response getResultElectionByShareholder(@PathParam("idShareholder")String idShareholder) {
		try {
			
			List<ResultElection> resultElections = meetingResultDao.getResultElectionByShareholder(idShareholder);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultElections))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all-result-election")
	public Response getAllResultElection() {
		logger.info("Get All Result Election");
		try {
			
			List<ResultElection> resultElections = meetingResultDao.getAllResultElection();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultElections))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get-result-election-by-meeting/{idMeeting}")
	public Response getResultElectionByMeeting(@PathParam("idMeeting")Integer idMeeting) {
		try {
			
			List<ResultElection> resultElections = meetingResultDao.getResultElectionByMeeting(idMeeting);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultElections))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get-result-election-by-election/{idElection}")
	public Response getResultElectionByElection(@PathParam("idElection")String idElection) {
		try {
			
			List<ResultElection> resultElections = meetingResultDao.getResultElectionByElection(idElection);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), resultElections))
					.build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_FAILED.getValue(),
							StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("voting/edit/{id}")
	public Response updateResultVoting(List<ResultVoting>resultVotings) {
		try {
			int update = meetingResultDao.updateShareHolderVoting(resultVotings);
			if(update==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					resultVotings)).build();
		} catch (Exception e) {
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("election/edit/{id}")
	public Response updateResultElection(List<ResultElection> resultElections) {
		try {
			int update = meetingResultDao.updateShareHolderElection(resultElections);
			if(update==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					resultElections)).build();
		} catch (Exception e) {
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}

}