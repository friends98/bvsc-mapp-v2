package service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
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
			
			long start =System.currentTimeMillis();
			List<ShareHolder> shareholders =fileUtils.readExcelFile(file);
			logger.info("Time out: "+(System.currentTimeMillis()-start));
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("upload-img")
	public Response uploadImage() {
		String file ="C:/Users/it-admin/Pictures/oto.jpg";
		String image =fileUtils.uploadImage(file);
		return Response.ok(new ApiResponse(
				StatusCode.UPLOAD_SUCCESS.getValue(),
				StatusCode.UPLOAD_SUCCESS.getDescription(), image)).build();
	}

}
