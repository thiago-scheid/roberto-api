package com.roberto.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class PrintZplTagRequest {

	@NotNull
	private String printerName;

	@NotNull
	private String zpl;
}