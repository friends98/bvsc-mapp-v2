package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
	private String id;
	private String username;
	private String password;
	private String idMeeting;
	private String idShareHolder;
	private Integer status;
	private Integer numberShare;
	private Integer numberShareAuth;
	private Integer role;

}
