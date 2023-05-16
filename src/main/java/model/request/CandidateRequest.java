package model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateRequest {
	private String idElection;
	private String fullname;
	private String birthDay;
	private String address;
	private String summaryInfo;
	

}
