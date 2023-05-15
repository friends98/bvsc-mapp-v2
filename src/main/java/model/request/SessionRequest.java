package model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionRequest {
	private String idShareholder;
	private String ipAddress;
	//private String browser;
	private String deviceType;

}
