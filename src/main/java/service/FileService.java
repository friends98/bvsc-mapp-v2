package service;

import java.io.File;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import common.StatusCode;
import dao.FileDao;
import model.ApiResponse;
import model.entity.ImageData;
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("upload-img")
	public Response uploadImage(@MultipartForm ImageData imageData) {
		
		String imageBase64 = Base64.getEncoder().encodeToString(imageData.getData());
		return Response.ok(new ApiResponse(
				StatusCode.UPLOAD_SUCCESS.getValue(),
				StatusCode.UPLOAD_SUCCESS.getDescription(), imageBase64)).build();
	}
	

	
	
	

}
