package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShareHolder {
	private String id;
	private String fullname;
	private String shareHolderCode;
	private String identityCard;
	private String email;
	private String address;
	private String phoneNumber;
	private String nationality;
	private String username;
	private String password;
	private Integer idMeeting;
	private Integer status;
	private Integer numberShares;
	private Integer numberSharesAuth;
	private Integer role;

}
