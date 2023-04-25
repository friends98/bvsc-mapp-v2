package model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class AuthErrorReponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String status;
	private String message;
	private Timestamp timestamp;
	
	public AuthErrorReponse() {}
	
	public AuthErrorReponse(String status, String message, Timestamp timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	
}
