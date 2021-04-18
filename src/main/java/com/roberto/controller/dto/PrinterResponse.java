package com.roberto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrinterResponse {

	private List<SystemPrinter> printers;
	private String message;
	private String system;
	private boolean status;
}
