package com.roberto.util.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = -5300005787375927621L;
	private String developerMessage;
	private String userMessage;
	private String moreInfo;
	
	@Builder.Default
	private long errorCode = 10000l;

	public ErrorDTO(String developerMessage, String userMessage) {
		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
		this.moreInfo = "Code" + this.errorCode;
	}

	public ErrorDTO(String developerMessage, String userMessage, long errorCode) {
		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
		this.moreInfo = "Code" + errorCode;
		this.errorCode = errorCode;
	}
}