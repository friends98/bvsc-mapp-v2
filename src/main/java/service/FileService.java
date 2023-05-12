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
import dao.FileDao;
import model.ApiResponse;
import model.entity.ShareHolder;
import utils.FileUtils;
@Path("")
public class FileService {
	private static final Logger logger = Logger.getLogger(FileService.class);	
	
	@Inject
	private FileDao fileDao;
	
	@Inject
	private FileUtils fileUtils;
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("upload-shardholder")
	public Response upload() {
		try {
			String file ="C:/Users/it-admin/Downloads/bvsc.xlsx";
			List<ShareHolder> shareholders =fileUtils.readExcelFile(file);
			int upload=fileDao.upload(shareholders);
			if(upload==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPLOAD_FAILED.getValue(),
						StatusCode.UPLOAD_FAILED.getDescription(), null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPLOAD_SUCCESS.getValue(),
					StatusCode.UPLOAD_SUCCESS.getDescription(), null)).build();

		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.UPLOAD_FAILED.getValue(),
					StatusCode.UPLOAD_FAILED.getDescription(), null)).build();
		}
	}

	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("upload")
//	public Response upload(ShareHolderRequest shareholderRequest) {
//		try {
//			ShareHolder shareholder = new ShareHolder();
//			shareholder.setFullname(shareholderRequest.getFullname());
//			shareholder.setIdentityCard(shareholderRequest.getIdentityCard());
//			shareholder.setEmail(shareholderRequest.getEmail());
//			shareholder.setAddress(shareholderRequest.getAddress());
//			shareholder.setPhoneNumber(shareholderRequest.getPhoneNumber());
//			shareholder.setUsername(shareholderRequest.getUsername());
//			shareholder.setPassword(shareholderRequest.getPassword());
//			shareholder.setNationality(shareholderRequest.getNationality());
//			shareholder.setIdMeeting(shareholderRequest.getIdMeeting());
//			shareholder.setStatus(shareholderRequest.getStatus());
//			shareholder.setNumberShares(shareholderRequest.getNumberShared());
//			shareholder.setNumberSharesAuth(shareholderRequest.getNumberAuthShared());
//			shareholder.setRole(shareholderRequest.getRole());
//			shareholder.setShareHolderCode(shareholderRequest.getShareHoldeCode());
//			fileDao.uploadFile(shareholder);
//			return Response.ok(new ApiResponse(
//					StatusCode.INSERT_SUCCESS.getValue(),
//					StatusCode.INSERT_SUCCESS.getDescription(), 
//					shareholder)).build();
//		} catch (Exception e) {
//			logger.error("ERROR INSERT : "+e.getMessage());
//			return Response.ok(new ApiResponse(
//					StatusCode.INSERT_FAILED.getValue(),
//					StatusCode.INSERT_FAILED.getDescription(),
//					null)).build();
//		}
//	}

}
