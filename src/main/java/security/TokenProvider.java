package security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Stateless
public class TokenProvider {
	private static final Logger logger = Logger.getLogger(TokenProvider.class);
	//private static final String KEY_AUTH = "bvsc";
	private String tokenSecret;
	private long tokenValidity;

	@PostConstruct
	public void init() {
		this.tokenSecret = "bvsc";
		this.tokenValidity = TimeUnit.MINUTES.toMillis(60);
	}

	public String generateToken(String username) {
		long now = new Date().getTime()+tokenValidity;
		Claims claims=Jwts.claims().setSubject(username);
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, tokenSecret)
				.setExpiration(new Date(now))
				.compact();
	}
	
	// get user name from jwt token 
	public String getUserNameFromJwtToken(String token) {
		Claims claims=Jwts.parser().setSigningKey(tokenSecret).parseClaimsJwt(token).getBody();
		String userName=claims.getSubject();
		return userName;
	}
	
	public Date getExpFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(tokenSecret)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
	}
	public boolean isTokenExpired(String token) {
		try {
			Date expiration=getExpFromJwtToken(token);
			return expiration.before(new Date(System.currentTimeMillis()));
		} catch (ExpiredJwtException e) {
			return true;
		}
	}
	
	
	// check validate token 
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.info("Invalid JWT: "+e.getMessage() );
		}
		return false;
	}

}
