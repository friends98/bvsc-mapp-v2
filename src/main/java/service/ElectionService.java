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

import org.apache.log4j.Logger;

import common.StatusCode;
import dao.ElectionDao;
import dao.daoimpl.ElectionDaoImpl;
import model.ApiResponse;
import model.entity.Election;
import model.request.ElectionRequest;

@Path("election")
public class ElectionService {
	
	private static final Logger logger = Logger.getLogger(ElectionService.class.getName());
	
	
	@Inject
	private ElectionDao<Election> electionDao;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response election(ElectionRequest electionReq) {
		electionDao = new ElectionDaoImpl();
		Election election = new Election();
		election.setIdMeeting(electionReq.getIdMeeting());
		election.setTitle(electionReq.getTitle());
		election.setDescription(electionReq.getDescription());
		election.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			int insert = electionDao.save(election);
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					election)).build();

		} catch (Exception e) {
			// TODO: handle exception
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
	public Response updateElection(@PathParam("id")String id,ElectionRequest electionReq) {
		electionDao = new ElectionDaoImpl();
		Election election = new Election();
		election.setId(id);
		election.setIdMeeting(electionReq.getIdMeeting());
		election.setTitle(electionReq.getTitle());
		election.setDescription(electionReq.getDescription());
		election.setModifiTime(new Timestamp(System.currentTimeMillis()));
		try {
			int edit=electionDao.update(election);
			if(edit==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					election)).build();
			
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
	public Response electionDetail(@PathParam("id")String id) {
		electionDao = new ElectionDaoImpl();
		try {
			Optional<Election> opElection=electionDao.getById(id);
			if(opElection.isEmpty()) {
				return Response.ok(new ApiResponse(
						StatusCode.DATA_FAILED.getValue(),
						StatusCode.DATA_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(),
					opElection.get())).build();
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
	public Response deleteElection(@PathParam("id")String id) {
		electionDao = new ElectionDaoImpl();
		try {
			Election election = new Election();
			election.setId(id);
			int delete=electionDao.delete(election);
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
