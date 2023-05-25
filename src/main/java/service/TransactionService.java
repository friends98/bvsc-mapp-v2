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
import dao.TransactionDao;
import model.ApiResponse;
import model.entity.AuthTransactionHistory;
import model.request.AuthTransactionRequest;
@Path("transaction")
public class TransactionService {
	
	private static final Logger logger = Logger.getLogger(TransactionService.class.getName());
	
	@Inject
	private TransactionDao transactionDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response meetings() {
		logger.info("Get All Transaction History");
		try {
			
			List<AuthTransactionHistory> authTransactionHistories = transactionDao.getAll();
			return Response.ok(
					new ApiResponse(
							StatusCode.DATA_SUCCESS.getValue(),
							StatusCode.DATA_SUCCESS.getDescription(), authTransactionHistories))
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
	public Response transaction(AuthTransactionRequest authTransReq) {
		AuthTransactionHistory authTransactionHistory = new AuthTransactionHistory();
		try {
			authTransactionHistory.setIdShareholder(authTransReq.getIdShareholder());
			authTransactionHistory.setIdShareholderAuth(authTransReq.getIdShareholderAuth());
			authTransactionHistory.setAmount(authTransReq.getAmount());
			int transaction = transactionDao.createTransactionShared(authTransactionHistory);
			if(transaction==0) {
				return Response.ok(new ApiResponse(
						StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(),
						null)).build();
			}
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(),
					null)).build();
		} catch (Exception e) {
			logger.error("ERROR: "+e.getMessage());
			return Response.ok(new ApiResponse(
					StatusCode.INSERT_FAILED.getValue(),
					StatusCode.INSERT_FAILED.getDescription(),
					null)).build();
		}
	}
}