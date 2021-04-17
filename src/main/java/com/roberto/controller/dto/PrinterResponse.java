package com.roberto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrinterResponse implements Serializable {

	private static final long serialVersionUID = 5821028638522309197L;

	// Lista de impressoras
	private List<SystemPrinter> printers;

	// Mensagem
	private String message;

	// Sistema
	private String system;

	// Status
	private boolean status;
}
