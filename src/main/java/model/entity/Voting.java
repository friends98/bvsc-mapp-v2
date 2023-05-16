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
public class Voting {
	private String id;
	private Integer idMeeting;
	private String content;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
}
