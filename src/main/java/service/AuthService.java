package service;

import java.sql.Timestamp;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.StatusCode;
import dao.AdminDao;
import dao.ShareHolderDao;
import model.AuthErrorReponse;
import model.AuthResponse;
import model.AdminAuthResponse;
import model.entity.Admin;
import model.entity.ShareHolder;
import model.request.LoginRequest;
import security.TokenProvider;

@Path("auth")
public class AuthService {
	private static final Logger logger =Logger.getLogger(AuthService.class);

	@Inject
	private TokenProvider tokenProvider;
	
//	@Inject
//	private AuthDao<UserInfo> userInfoDao;
	
	@Inject 
	private AdminDao<Admin> adminDao;
	
	@Inject
	private ShareHolderDao<ShareHolder> shareholderDao;



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(LoginRequest loginRequest) {
		
		try {
			String username = loginRequest.getUsername();
			String password = loginRequest.getPassword();

			Optional<ShareHolder> opShareholderInfo=shareholderDao.findByUserNameAndPassword(username, password);
			if(opShareholderInfo.isEmpty()) {
				return Response.ok(new AuthErrorReponse(
						StatusCode.LOGIN_FAILED.getValue(),
						StatusCode.LOGIN_FAILED.getDescription(),
						new Timestamp(System.currentTimeMillis())))
						.build();
			}
			String jwtToken = tokenProvider.generateToken(username);
			return Response.ok(
					new AuthResponse(
							opShareholderInfo.get().getId(),
							username, 
							jwtToken,
							tokenProvider.getExpFromJwtToken(jwtToken).getTime(), 
							opShareholderInfo.get().getRole()))
					.build();

		} catch (NullPointerException e) {
			logger.info("Password is not correct!");
			return Response.ok(new AuthErrorReponse(
					StatusCode.LOGIN_FAILED.getValue(),
					StatusCode.LOGIN_FAILED.getDescription(),
					new Timestamp(System.currentTimeMillis())))
					.build();
		}

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("admin/login")
	public Response adminLogin(LoginRequest loginRequest) {
		try {
			String username = loginRequest.getUsername();
			String password = loginRequest.getPassword();
			Optional<Admin> opAdmin = adminDao.findByUserNameAndPassword(username, password);
			if(opAdmin.isEmpty()) {
				return Response.ok(new AuthErrorReponse(
						StatusCode.LOGIN_FAILED.getValue(),
						StatusCode.LOGIN_FAILED.getDescription(),
						new Timestamp(System.currentTimeMillis())))
						.build();
			}
			String jwtToken = tokenProvider.generateToken(username);
			return Response.ok(
					new AdminAuthResponse(
							opAdmin.get().getId(),
							username, 
							jwtToken,
							tokenProvider.getExpFromJwtToken(jwtToken)
							.getTime(), opAdmin.get().getIdCompany()))
					.build();
		} catch (Exception e) {
			return Response.ok(new AuthErrorReponse(
					StatusCode.LOGIN_FAILED.getValue(),
					StatusCode.LOGIN_FAILED.getDescription(),
					new Timestamp(System.currentTimeMillis())))
					.build();
		}
	}
	
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("check")
	public Response isExpired() {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxdWFuZyIsImV4cCI6MTY4MjQxMDQ5N30.Hn8H7zEAUTBvp-c_v9K8ZpDnAccax3eywVOzVnQEYW0";
		String check=tokenProvider.isTokenExpired(token)?"not ok":"ok";
		return Response.ok(check).build();

	}

}
