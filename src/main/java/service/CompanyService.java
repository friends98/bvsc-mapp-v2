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
import dao.CompanyDao;
import dao.daoimpl.CompanyDaoImpl;
import model.ApiResponse;
import model.entity.CompanyInfo;
import model.request.CompanyRequest;

@Path("company")
public class CompanyService {
	private static final Logger logger = Logger.getLogger(ShareHolderService.class);
	Gson gson = new Gson();
	@Inject
	private CompanyDao<CompanyInfo> companyDaoImpl;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response shareholderDetails(@PathParam("id") Integer id) {
		logger.info("ID request: " + id);
		try {
			companyDaoImpl = new CompanyDaoImpl();
			Optional<CompanyInfo> opCompany = companyDaoImpl.getById(id);
			if (opCompany.isEmpty()) {
				return Response.ok(new ApiResponse(
						StatusCode.DATA_FAILED.getValue(),
						StatusCode.DATA_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(),
					opCompany.get())).build();

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
	public Response Companys() {
		logger.info("Get All Company");
		try {
			List<CompanyInfo> companyInfos = companyDaoImpl.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), companyInfos))
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
	public Response companyResponse(CompanyRequest companyRequest) {
		CompanyInfo companyInfo = new CompanyInfo();
		try {
			companyInfo.setCompanyName(companyRequest.getCompanyName());
			companyInfo.setStockCode(companyRequest.getStockCode());
			companyInfo.setTaxCode(companyRequest.getTaxCode());
			companyInfo.setAddress(companyRequest.getAddress());
			companyInfo.setFoundedYear(companyRequest.getFoundedYear());
			int insert = companyDaoImpl.save(companyInfo);
			if(insert==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), 
					companyInfo)).build();
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
	public Response updateCompany(@PathParam("id")Integer id,CompanyRequest companyRequest) {
		CompanyInfo companyInfo = new CompanyInfo();
		try {
			companyInfo.setId(id);
			companyInfo.setCompanyName(companyRequest.getCompanyName());
			companyInfo.setStockCode(companyRequest.getStockCode());
			companyInfo.setTaxCode(companyRequest.getTaxCode());
			companyInfo.setAddress(companyRequest.getAddress());
			companyInfo.setFoundedYear(companyRequest.getFoundedYear());
			int edit = companyDaoImpl.update(companyInfo);
			if(edit==0) {
				return Response.ok(new ApiResponse(
						StatusCode.UPDATE_FAILED.getValue(),
						StatusCode.UPDATE_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.UPDATE_SUCCESS.getValue(),
					StatusCode.UPDATE_SUCCESS.getDescription(), 
					companyInfo)).build();
			
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
	public Response deleteCompany(@PathParam("id")Integer id) {
		CompanyInfo companyInfo = new CompanyInfo();
		try {
			companyInfo.setId(id);
			int delete = companyDaoImpl.delete(companyInfo);
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
