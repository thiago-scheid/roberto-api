package com.roberto.api.exception;

public class PrinterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4279305450080078146L;

	public PrinterNotFoundException(String printerName) {
		super("Printer " + printerName + " not found");
	}
}
