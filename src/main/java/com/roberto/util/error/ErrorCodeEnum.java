package com.roberto.util.error;

import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {

	MISSING_PARAMETERS(20001L, HttpStatus.BAD_REQUEST),
	UNKNOWN_PARAMETERS(20006L, HttpStatus.BAD_REQUEST),
	MISSING_REQUEST_BODY(20019L, HttpStatus.BAD_REQUEST),
	NOT_FOUND(20023L, HttpStatus.NOT_FOUND),
	ALREADY_EXISTS(20033L, HttpStatus.CONFLICT),
	INTERNAL_SERVER_ERROR(10000L, HttpStatus.INTERNAL_SERVER_ERROR);
	
	private Long code;	
	private HttpStatus httpCode;
	
	private ErrorCodeEnum(Long code, HttpStatus httpCode) {
		this.code = code;
		this.httpCode = httpCode;
	}

	public Long getCode() {
		return code;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}
		
	public static HttpStatus findHttpStatus(Long errorCode) {
		
		for (ErrorCodeEnum error : ErrorCodeEnum.values()) {
			if(error.getCode().equals(errorCode)) {
				return error.getHttpCode();
			}
		}
		
		return null;
	}	
}