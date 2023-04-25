package model;

public class ApiResponse {

	private String status;
	private String message;
	private Object items;

	public ApiResponse(String status, String message, Object items) {
		this.status = status;
		this.message = message;
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

}
