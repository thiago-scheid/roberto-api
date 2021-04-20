package com.roberto.api.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class PrintZplTagRequest {

	@NotNull(message = "Printer name may not be null")
	@NotEmpty(message = "Printer name may not be empty")
	private String printerName;

	@NotNull(message = "Code zpl may not be null")
	@NotEmpty(message = "Code zpl may not be empty")
	private String codeZpl;
}