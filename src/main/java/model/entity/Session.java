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
public class Session {
	private String sessionId;
	private String idShareholder;
	private Date startTime;
	private Date endTime;
	private String ipAddress;
	private String deviceType;

}
