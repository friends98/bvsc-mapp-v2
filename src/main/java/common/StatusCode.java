package common;

public enum StatusCode {
	
	LOGIN_SUCCESS(1,"Login successfully!"),
	LOGIN_FAILED(2,"Login failed!"),
	EXPIRED_TIME(2,"Token is expired time"),
	INSERT_SUCCESS(1,"Insert data successfully"),
	INSERT_FAILED(2,"Insert data failed")
	;
	
	
	
	
	//
	private final Integer value;
	private final String description;
	
	
	private StatusCode(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	
}
