package com.roberto.controller.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class PrintZplTagResponse implements Serializable {

	private static final long serialVersionUID = 2193015566326495334L;

	private String message;

	public PrintZplTagResponse(String message) {
		this.message = message;
	}
}
