package model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShareHolderRequest {
	//
	private String fullname;
	private String identityCard;
	private String email;
	private String address;
	private String phoneNumber;
	private String nationality;
	//
	private String username;
	private String password;
	private Integer idMeeting;
	//private Integer idShareholder;
	private Integer status; // status account join meeting
	private Integer numberShared;
	private Integer numberAuthShared;
	private Integer role;
	private String shareHoldeCode;
	

}
