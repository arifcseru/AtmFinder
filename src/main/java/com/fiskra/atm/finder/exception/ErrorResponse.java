package com.fiskra.atm.finder.exception;

public class ErrorResponse {
	
	private int status;

	private int code;
	
	private String message;
	
	private String developerMessage;
	
	private String moreInfoURL;
	
	public ErrorResponse(int status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public ErrorResponse(int status, int code, String message,String developerMessage,String moreInfoURL) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.moreInfoURL = moreInfoURL;	
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getMoreInfoURL() {
		return moreInfoURL;
	}

	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

	

}
