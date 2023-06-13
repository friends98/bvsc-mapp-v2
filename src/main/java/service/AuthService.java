package service;

import java.sql.Timestamp;
import java.util.List;
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
import dao.daoimpl.AdminDaoImpl;
import model.AdminAuthResponse;
import model.ApiResponse;
import model.AuthErrorReponse;
import model.AuthResponse;
import model.entity.Admin;
import model.entity.ShareHolder;
import model.request.LoginRequest;
import security.TokenProvider;

@Path("auth")
public class AuthService {
	private static final Logger logger = Logger.getLogger(AuthService.class);

	@Inject
	private TokenProvider tokenProvider;

//	@Inject
//	private AuthDao<UserInfo> userInfoDao;

	@Inject
	private AdminDao<Admin> adminDao;

	@Inject
	private ShareHolderDao<ShareHolder> shareholderDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response admins() {
		logger.info("Get All Admin");
		try {

			List<Admin> admins = adminDao.getAll();
			return Response.ok(new ApiResponse(StatusCode.DATA_SUCCESS.getValue(),
					StatusCode.DATA_SUCCESS.getDescription(), admins)).build();
		} catch (Exception e) {
			return Response.ok(
					new ApiResponse(StatusCode.DATA_FAILED.getValue(), StatusCode.DATA_FAILED.getDescription(), null))
					.build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(LoginRequest loginRequest) {

		try {
			String username = loginRequest.getUsername();
			String password = loginRequest.getPassword();

			Optional<ShareHolder> opShareholderInfo = shareholderDao.findByUserNameAndPassword(username, password);
			if (opShareholderInfo.isEmpty()) {
				return Response
						.ok(new AuthErrorReponse(StatusCode.LOGIN_FAILED.getValue(),
								StatusCode.LOGIN_FAILED.getDescription(), new Timestamp(System.currentTimeMillis())))
						.build();
			}
			String jwtToken = tokenProvider.generateToken(username);
			return Response
					.ok(new AuthResponse(opShareholderInfo.get().getId(), username, jwtToken,
							tokenProvider.getExpFromJwtToken(jwtToken).getTime(), opShareholderInfo.get().getRole()))
					.build();

		} catch (NullPointerException e) {
			logger.info("Password is not correct!");
			return Response
					.ok(new AuthErrorReponse(StatusCode.LOGIN_FAILED.getValue(),
							StatusCode.LOGIN_FAILED.getDescription(), new Timestamp(System.currentTimeMillis())))
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
			if (opAdmin.isEmpty()) {
				return Response
						.ok(new AuthErrorReponse(StatusCode.LOGIN_FAILED.getValue(),
								StatusCode.LOGIN_FAILED.getDescription(), new Timestamp(System.currentTimeMillis())))
						.build();
			}
			String jwtToken = tokenProvider.generateToken(username);
			return Response.ok(new AdminAuthResponse(opAdmin.get().getId(), username, jwtToken,
					tokenProvider.getExpFromJwtToken(jwtToken).getTime(), opAdmin.get().getRole())).build();
		} catch (Exception e) {
			return Response
					.ok(new AuthErrorReponse(StatusCode.LOGIN_FAILED.getValue(),
							StatusCode.LOGIN_FAILED.getDescription(), new Timestamp(System.currentTimeMillis())))
					.build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("create")
	public Response createAccountPreside(LoginRequest loginRequest) {
		adminDao = new AdminDaoImpl();
		// check request
		try {
			Admin admin = new Admin();
			admin.setUsername(loginRequest.getUsername());
			admin.setPassword(loginRequest.getPassword());
			admin.setRole(loginRequest.getRole());

			int insert = adminDao.save(admin);
			if (insert == 0) {
				return Response.ok(new ApiResponse(StatusCode.INSERT_FAILED.getValue(),
						StatusCode.INSERT_FAILED.getDescription(), null)).build();
			}
			return Response.ok(new ApiResponse(StatusCode.INSERT_SUCCESS.getValue(),
					StatusCode.INSERT_SUCCESS.getDescription(), admin)).build();

		} catch (Exception e) {
			logger.error("ERROR INSERT : " + e.getMessage());
			return Response.ok(new ApiResponse(StatusCode.INSERT_FAILED.getValue(),
					StatusCode.INSERT_FAILED.getDescription(), null)).build();
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("check")
	public Response isExpired() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxdWFuZyIsImV4cCI6MTY4MjQxMDQ5N30.Hn8H7zEAUTBvp-c_v9K8ZpDnAccax3eywVOzVnQEYW0";
		String check = tokenProvider.isTokenExpired(token) ? "not ok" : "ok";
		return Response.ok(check).build();

	}

}
