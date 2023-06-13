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
public class ElectionRequest {
	private String id;
	private Integer idMeeting;
	private String title;
	private String description;
	private Integer numberOfElected;
	private Date createdTime;
	private Date modifiedTime;
	private Integer status;
}
