package model;

public class ApiResponse {

	private Integer status;
	private String message;
	private Object items;

	public ApiResponse(Integer status, String message, Object items) {
		this.status = status;
		this.message = message;
		this.items = items;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
