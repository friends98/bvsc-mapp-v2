package service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("save-election")
//	public Response saveResultElection(List<ResultElection> resultElections) {
//		try {
//			int save = meetingResultDao.saveShareHolderElection(resultElections);
//		} catch (Exception e) {
//			// TODO: handle exception
//		} 
//	}

}
