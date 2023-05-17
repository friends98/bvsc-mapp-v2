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

import com.google.gson.Gson;

import common.StatusCode;
import dao.ShareHolderDao;
import dao.daoimpl.ShareHolderDaoImpl;
import model.ApiResponse;
import model.entity.ShareHolder;
import model.request.ShareHolderRequest;

@Path("shareholder")
public class ShareHolderService {
	private static final Logger logger = Logger.getLogger(ShareHolderService.class);
	Gson gson = new Gson();
	@Inject
	private ShareHolderDao<ShareHolder> shareHolderImpl;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response shareholderDetails(@PathParam("id") String id) {
		logger.info("ID request: " + id);
		try {
			shareHolderImpl = new ShareHolderDaoImpl();
			Optional<ShareHolder> opShareHolder = shareHolderImpl.getById(id);
			if (opShareHolder.isEmpty()) {
				return Response.ok(new ApiResponse(
						StatusCode.DATA_FAILED.getValue(),
						StatusCode.DATA_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(),
					opShareHolder.get())).build();

		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.DATA_FAILED.getValue(),
					StatusCode.DATA_FAILED.getDescription(),
					null)).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response shareHolders() {
		logger.info("Get All Shareholder");
		try {
			
			List<ShareHolder> shareHolders = shareHolderImpl.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), shareHolders))
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
	public Response shareholder(ShareHolderRequest shareholderRequest) {
		ShareHolder shareholder = new ShareHolder();
		try {
			shareholder.setFullname(shareholderRequest.getFullname());
			shareholder.setShareHolderCode(shareholderRequest.getShareHolderCode());
			shareholder.setIdentityCard(shareholderRequest.getIdentityCard());
			shareholder.setEmail(shareholderRequest.getEmail());
			shareholder.setAddress(shareholderRequest.getAddress());
			shareholder.setPhoneNumber(shareholderRequest.getPhoneNumber());
			shareholder.setUsername(shareholderRequest.getUsername());
			shareholder.setPassword(shareholderRequest.getPassword());
			shareholder.setNationality(shareholderRequest.getNationality());
			shareholder.setIdMeeting(shareholderRequest.getIdMeeting());
			shareholder.setStatus(shareholderRequest.getStatus());
			shareholder.setNumberShares(shareholderRequest.getNumberShares());
			shareholder.setNumberSharesAuth(shareholderRequest.getNumberSharesAuth());
			shareholder.setRole(shareholderRequest.getRole());
			int insert = shareHolderImpl.save(shareholder);
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					shareholder)).build();
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
	public Response updateShareHolder(@PathParam("id")String id,ShareHolderRequest shareholderRequest) {
		ShareHolder shareholder = new ShareHolder();
		try {
			shareholder.setId(id);
			shareholder.setFullname(shareholderRequest.getFullname());
			shareholder.setShareHolderCode(shareholderRequest.getShareHolderCode());
			shareholder.setIdentityCard(shareholderRequest.getIdentityCard());
			shareholder.setEmail(shareholderRequest.getEmail());
			shareholder.setAddress(shareholderRequest.getAddress());
			shareholder.setPhoneNumber(shareholderRequest.getPhoneNumber());
			shareholder.setUsername(shareholderRequest.getUsername());
			shareholder.setPassword(shareholderRequest.getPassword());
			shareholder.setNationality(shareholderRequest.getNationality());
			shareholder.setIdMeeting(shareholderRequest.getIdMeeting());
			shareholder.setStatus(shareholderRequest.getStatus());
			shareholder.setNumberShares(shareholderRequest.getNumberShares());
			shareholder.setNumberSharesAuth(shareholderRequest.getNumberSharesAuth());
			shareholder.setRole(shareholderRequest.getRole());
			int edit = shareHolderImpl.update(shareholder);
			if(edit==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					shareholder)).build();
			
		} catch (Exception e) {
			logger.error("ERROR UPDATE : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_FAILED.getValue(),
					StatusCode.UPDATE_FAILED.getDescription(),
					null)).build();
		}
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteShareHolder(@PathParam("id")String id) {
		ShareHolder shareholder = new ShareHolder();
		try {
			shareholder.setId(id);
			int delete = shareHolderImpl.delete(shareholder);
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
	public Response getAllByMeeting(@PathParam("idMeeting")String idMeeting) {
		logger.info("Get All Shareholder");
		try {
			
			List<ShareHolder> shareHolders = shareHolderImpl.getByIdMeeting(idMeeting);
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), shareHolders))
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
