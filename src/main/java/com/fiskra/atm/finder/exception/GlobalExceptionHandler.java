package com.fiskra.atm.finder.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { AtmException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse restControllerException(HttpServletRequest req, AtmException ex) {
		logger.error("Request:"+ req.getRequestURI() +" raised " + ex.getMessage());
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getExceptionCode().getCode(),
				ex.getExceptionCode().getMessage());
		
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse urlNotFound(NoHandlerFoundException ex) {
		logger.error("Request raised " + ex);
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ExceptionCodes.URL_NOT_FOUND.getCode(),
				ExceptionCodes.URL_NOT_FOUND.getMessage());
	}
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse unknownException(Exception ex) {
		logger.error("Request raised " + ex);
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				ExceptionCodes.INTERNAL_SERVER_ERROR.getCode(),
				ExceptionCodes.INTERNAL_SERVER_ERROR.getMessage());
	}
	

}
