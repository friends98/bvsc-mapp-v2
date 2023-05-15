package service;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.StatusCode;
import dao.SessionDao;
import model.request.SessionRequest;
import model.ApiResponse;
import model.entity.Session;

@Path("session")
public class SessionService {
	private static final Logger logger =Logger.getLogger(SessionService.class.getName());
	@Inject
	private SessionDao sessionDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertSession(SessionRequest sessionReq) {
		Session session = new Session ();
		try {
			session.setIdShareholder(sessionReq.getIdShareholder());
			session.setIpAddress(sessionReq.getIpAddress());
			session.setDeviceType(sessionReq.getDeviceType());
			session.setStartTime(new Timestamp(System.currentTimeMillis()));
			int insert = sessionDao.insertSession(session);
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					session)).build();
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
	public Response updateSession(@PathParam("id")String id,SessionRequest sessionReq) {
		Session session = new Session ();
		try {
			session.setSessionId(id);
			session.setEndTime(new Timestamp(System.currentTimeMillis()));
			int update = sessionDao.updateSession(session);
			if(update==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					session)).build();
		} catch (Exception e) {
			logger.error("ERROR UPDATE : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}
}
