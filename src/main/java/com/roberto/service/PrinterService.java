package com.roberto.service;

import com.roberto.controller.dto.SystemPrinter;
import com.roberto.enums.TemplateTagType;
import com.roberto.exception.PrinterException;
import java.io.IOException;
import java.util.List;

import javax.print.PrintService;

public interface PrinterService {

	List<SystemPrinter> getPrinterServer();
	
	PrintService detectPrinter(String printer);
	
	boolean printTags(String printerIdentifier, Object tagBody, TemplateTagType templateType)
			throws PrinterException, IOException;
}
