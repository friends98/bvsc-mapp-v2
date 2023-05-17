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
public class MeetingInfoRequest {
	private Integer id;
	private Integer idCompany;
	private String nameMeeting;
	private Integer numberOrganized;
	private Date yearOrganized;
	private Integer status;
	private String imageBanner;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
	private Date startTime;
//	private String imagePath;
	private Date endTime;
	private String address;

}
