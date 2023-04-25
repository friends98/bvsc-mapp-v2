package model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyInfo {
	private String id;
	private String companyName;
	private String stockCode;
	private Integer taxCode;
	private String address;
	private Date foundYear;
	/**
	 * @param id
	 * @param companyName
	 * @param stockCode
	 * @param taxCode
	 * @param address
	 * @param foundYear
	 */
	
	
}
