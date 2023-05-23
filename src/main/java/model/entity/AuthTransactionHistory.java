package model.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthTransactionHistory {
	private String id;
	private String idShareholder;
	private String idShareholderAuth;
	private Integer amount;
	private Date createdAt;
	private Date updatedAt;

}
