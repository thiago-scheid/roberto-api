package com.roberto.controller.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1342031702110664399L;
	
	private String userMessage;
	private String developerMessage;
	private long code;
}
