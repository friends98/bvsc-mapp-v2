package model.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResultElection {
	private String id;
	private String idElection;
	private String idUser;
	private Integer numberShareCandidate;
	private Timestamp timeElection;
}
