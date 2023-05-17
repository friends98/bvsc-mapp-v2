package model.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeetingInfo {
	private Integer id;
	private Integer idCompany;
	private String nameMeeting;
	private Integer numberOrganized;
	private Date yearOrganized;
	private Integer status;
	private String imageBanner;
	private Timestamp startTime;
	private Timestamp endTime;
	private String address;
}
