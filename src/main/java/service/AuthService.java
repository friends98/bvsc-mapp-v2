package service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import common.Constants;
import model.ApiResponse;
import model.AuthErrorReponse;
import model.AuthResponse;
import model.request.LoginRequest;
import security.SecurityUtils;
import security.TokenProvider;

@Path("auth")
public class AuthService {
	private static final Logger logger =Logger.getLogger(AuthService.class);

	@Inject
	private TokenProvider tokenProvider;

	@Inject
	private SecurityUtils securityUtils;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		String hashPassword = securityUtils.getMd5(password);
		if (!hashPassword.equals("e10adc3949ba59abbe56e057f20f883e")) {
			// throw exception
			logger.info("Password is not correct!");
			return Response.ok(new AuthErrorReponse(
					Constants.HTTP_CODE_401,
					Constants.LOGIN_FAILED,
					new Timestamp(System.currentTimeMillis())))
					.build();
		}
		String jwtToken = tokenProvider.generateToken(username);
		Set<String> roles = new HashSet<>();
		return Response.ok(
				new AuthResponse(
						username, 
						jwtToken,
						tokenProvider.getExpirationByJwtToken(jwtToken)
						.getTime(), 
						roles))
				.build();

	}

}
