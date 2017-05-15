package com.fiskra.atm.finder.exception;

public class AtmException extends Exception {

	private static final long serialVersionUID = -4399204266422724680L;
	
	private ExceptionCodes exceptionCode;
	
	public AtmException() {
		super();
	}
	
	public AtmException(ExceptionCodes exceptionCode){
		this.exceptionCode = exceptionCode;
	}

	public ExceptionCodes getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(ExceptionCodes exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

}
