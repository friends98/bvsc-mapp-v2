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
public class SessionRequest {
	private String sessionId;
	private String idShareholder;
	private Date startTime;
	private Date endTime;
	private String ipAddress;
	private String deviceType;
}
