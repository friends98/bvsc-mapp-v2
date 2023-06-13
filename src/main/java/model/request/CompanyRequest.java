package model.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyRequest {
	private Integer id;
	private String companyName;
	private String stockCode;
	private Integer taxCode;
	private String address;
	private Timestamp foundedYear;
	
}
