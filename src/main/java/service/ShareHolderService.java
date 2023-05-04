package service;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.Constants;
import dao.ShareHolderDao;
import dao.daoimpl.ShareHolderDaoImpl;
import model.ApiResponse;
import model.entity.ShareHolderInfo;

@Path("shareholder")
public class ShareHolderService {
	private static final Logger logger = Logger.getLogger(ShareHolderService.class); 
	
	
	@Inject
	private ShareHolderDao<ShareHolderInfo> shareHolderImpl;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response get(@PathParam("id")String id) {
		shareHolderImpl=new ShareHolderDaoImpl();
		Optional<ShareHolderInfo> opShareHolderInfo=shareHolderImpl.getById(id);
		if(!opShareHolderInfo.isPresent()) {
			logger.info("khong tim thay");
		}
		return Response.ok(new ApiResponse(Constants.HTTP_CODE_200,
				Constants.SUCCESS,
				opShareHolderInfo.get())).build();
	}

}
