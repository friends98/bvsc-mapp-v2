/**
 * 
 */
package service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.StatusCode;
import dao.CandidateDao;
import model.ApiResponse;
import model.entity.CompanyInfo;

/**
 * @author it-admin
 *
 */
@Path("")
public class AccountService {
	
	//private static final Logger logger = Logger.getLogger(AccountService.class);
	@Inject 
	private CandidateDao candidateDao;

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@OPTIONS
	@Path("/company")
	public Response testSql() {
//		ConnectionUtils conn=ConnectionUtils.getInstance();
//		return Response.status(200).entity(conn.toString()).build();
		List<CompanyInfo> response=candidateDao.getAllCandidate();
		return Response.ok(new ApiResponse(
				StatusCode.LOGIN_SUCCESS.getValue(),
				StatusCode.LOGIN_SUCCESS.getDescription(),
				response)).build();
	}
}
