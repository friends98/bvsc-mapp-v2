package model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminAuthResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String token;
	private long exp;
	private Integer role;
	
	public AdminAuthResponse() {
	}
	
	public AdminAuthResponse(String id, String username, String token, long exp, Integer role) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.exp = exp;
		this.role = role;
	}

	
	

}
