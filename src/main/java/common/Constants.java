package common;

public class Constants {

	//

	public static final String SUCCESS = "Success get information record";
	public static final String ERROR = "Error get information record";
	public static final String REQUEST_VALID_MESS = "Request is Valid";
	public static final String REQUEST_INVALID_MESS = "Request is Invalid";

	// http status code
	public static final Integer HTTP_CODE_200 = 200;
	public static final Integer HTTP_CODE_400 = 400;
	public static final Integer HTTP_CODE_401 = 401;
	public static final Integer HTTP_CODE_403 = 403;
	public static final Integer HTTP_CODE_500 = 500;

	// status response
	public static final Integer LOGIN_SUCCESS = 1;
	public static final Integer LOGIN_FAILED = 2;

	// role authorize number
	// public static final Integer ADMIN = 1;
	// public static final Integer SHARE_HOLDER = 2;
	// public static final Integer PRESIDENT = 3;

	//
	public static final Integer ADMIN = 0;
	public static final Integer SHARE_HOLDERS = 1;
	public static final Integer PRESIDENT = 2;
	
	
	public static final Integer MEETING_INIT=0;
	public static final Integer MEETTING_RUN=1;
	public static final Integer MEETTING_END=2;
	
	public static final Integer BATCH_SIZE=20000;
	
	// status voting
	public static final Integer APPROVED=1;
	public static final Integer DISAPPROVED=2;
	public static final Integer NO_COMMENT=0;
	
}
