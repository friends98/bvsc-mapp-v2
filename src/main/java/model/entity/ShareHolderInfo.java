package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShareHolderInfo {
	private String id;
	private String fullname;
	private String identityCard;
	private String email;
	private String address;
	private String phoneNumber;
	private String nationality;

}
