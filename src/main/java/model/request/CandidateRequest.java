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
public class CandidateRequest {
	private Integer id;
	private String idElection;
	private String fullName;
	private Date birthday;
	private String address;
	private String summaryInfo;
}
