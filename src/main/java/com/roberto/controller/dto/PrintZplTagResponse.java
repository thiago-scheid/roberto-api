package com.roberto.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrintZplTagResponse {

	private String message;
	private String system;
	private boolean status;
}
