package model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateResponse {
	private String fullname;
	private String birthDay;
	private String address;
	private String summaryInfo;
}
