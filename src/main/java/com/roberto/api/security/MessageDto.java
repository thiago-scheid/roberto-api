package com.roberto.api.security;

import java.io.Serializable;

public class MessageDto implements Serializable {
	private static final long serialVersionUID = -8786674079654011911L;

	private String developerMessage;
	private String userMessage;
	private long errorCode = 10000l;

	public MessageDto(String developerMessage, String userMessage) {
		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
	}

	public MessageDto(String developerMessage, String userMessage, long errorCode) {
		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
		this.errorCode = errorCode;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public long getErrorCode() {
		return errorCode;
	}
}
