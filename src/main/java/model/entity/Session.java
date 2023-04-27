package model.entity;

import java.sql.Time;
import java.sql.Timestamp;

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
	private String idUser;
	private Timestamp startTime;
	private Timestamp endTime;
	private Time duration;
	private String ipAddress;
	private String browser;
	private String deviceType;
	private String location;

}
