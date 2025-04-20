package com.example.HRMS.DTO;

public class ResponseMessage {
private String message;
private Boolean success;
public ResponseMessage(String message, Boolean success) {
	super();
	this.message = message;
	this.success = success;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Boolean getSuccess() {
	return success;
}
public void setSuccess(Boolean success) {
	this.success = success;
}

}
