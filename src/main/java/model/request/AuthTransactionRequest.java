package model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthTransactionRequest {

	private String idShareholder;
	private String idShareholderAuth;
	private Integer amount;
	
}