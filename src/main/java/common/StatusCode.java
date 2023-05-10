package common;

public enum StatusCode {
	
	LOGIN_SUCCESS(1,"Login successfully!"),
	LOGIN_FAILED(2,"Login failed!"),
	EXPIRED_TIME(2,"Token is expired time"),
	INSERT_SUCCESS(1,"Insert data successfully"),
	INSERT_FAILED(2,"Insert data failed"),
	DATA_SUCCESS(1,"Get record data successfully"),
	DATA_FAILED(2,"Get record data failed!"),
	UPDATE_SUCCESS(1,"Update record successfully"),
	UPDATE_FAILED(2,"Update record failed!"),
	DELETE_SUCCESS(1,"Delete record successfully"),
	DELETE_FAILED(2,"Delete record failed!")
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
