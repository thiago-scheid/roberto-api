package com.roberto.api.service;

import com.roberto.api.controller.dto.SystemPrinter;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.exception.PrinterException;
import java.io.IOException;
import java.util.List;
import javax.print.PrintService;

public interface PrinterService {

	List<SystemPrinter> getPrinterServer();

	PrintService detectPrinter(String printer);

	boolean printTags(String printerIdentifier, Object tagBody, TemplateTagType templateType)
			throws PrinterException, IOException;
}
