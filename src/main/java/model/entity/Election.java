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
public class Election {
	private String id;
	private Integer idMeeting;
	private String title;
	private String description;
	private Date createdTime;
	private Date modifiedTime;
}
