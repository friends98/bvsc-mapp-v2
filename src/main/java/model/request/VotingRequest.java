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
public class VotingRequest {
	private String id;
	private String idMeeting;
	private String content;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
}
