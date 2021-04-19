package com.roberto.api.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class PrintZplTagRequest {

	@NotNull
	private String printerName;

	@NotNull
	private String codeZpl;
}