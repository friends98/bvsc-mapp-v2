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
public class VotingRequest {
	private String id;
	private Integer idMeeting;
	private String content;
	private Date createdTime;
	private Date modifiedTime;
	private Integer status;
}
