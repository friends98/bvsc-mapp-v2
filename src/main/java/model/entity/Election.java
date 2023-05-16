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
public class Election {
	private String id;
	private Integer idMeeting;
	private String title;
	private String description;
	private Timestamp createTime;
	private Timestamp modifiTime;
}
