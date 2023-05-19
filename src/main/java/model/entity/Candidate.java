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
public class Candidate {
	private Integer id;
	private String idElection;
	private String fullname;
	private Date birthday;
	private String address;
	private String summaryInfo;
}
