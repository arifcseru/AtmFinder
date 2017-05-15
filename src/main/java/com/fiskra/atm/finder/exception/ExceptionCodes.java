package com.fiskra.atm.finder.exception;

public enum ExceptionCodes {

	URL_NOT_FOUND(1001, "Url not found", ""),
	CITY_NOT_FOUND(1002, "City not found", ""),
	ADDRESS_NOT_FOUND(1003, "Address not found", ""),
	ATM_NOT_FOUND(1004, "Atm not found", ""),
	INTERNAL_SERVER_ERROR(1005, "Uexpected situation occured.", "");

	private ExceptionCodes(int code, String message, String developerMessage) {
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	private int code;
	private String message;
	private String developerMessage;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

}
