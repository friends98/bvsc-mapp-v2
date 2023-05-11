package model.request;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeetingInfoRequest {
	private Integer id;
	private String idCompany;
	private String nameMeeting;
	private Integer numberOrganized;
	private Date yearOrganized;
	private Integer status;
	private String imageBanner;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
	private String startTime;
	
	private String endTime;
	private String address;

}
