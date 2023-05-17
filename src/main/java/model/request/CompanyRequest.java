package model.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyRequest {
	private Integer idInteger;
	private String companyName;
	private String stockCode;
	private Integer taxCode;
	private String address;
	private Date foundYear;
	
}
