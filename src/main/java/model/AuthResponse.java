package model;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class AuthResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String token;
	private long exp;
	private Set<String> roles;

	public AuthResponse() {
	}

	public AuthResponse(String username, String token, Long exp, Set<String> roles) {
		super();
		this.username = username;
		this.token = token;
		this.exp = exp;
		this.roles = roles;
	}

}
