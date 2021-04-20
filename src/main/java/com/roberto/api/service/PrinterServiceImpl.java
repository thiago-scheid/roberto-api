package com.roberto.api.service;

import com.roberto.api.controller.dto.SystemPrinter;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.exception.PrinterException;
import com.roberto.api.factories.TemplateTagFactory;
import com.roberto.api.template.TagTemplateBase;
import com.roberto.api.util.PrinterTagConfig;
import org.springframework.stereotype.Service;
import javax.print.PrintException;
import javax.print.PrintService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class PrinterServiceImpl implements PrinterService {

	public List<SystemPrinter> getPrinterServer() {

		return PrinterTagConfig.getPrinterServer();
	}

	public PrintService detectPrinter(String printer) {

		return PrinterTagConfig.detectPrinter(printer);
	}
	
	public boolean printTags(String printerIdentifier, Object tagBody, TemplateTagType templateType)
			throws PrinterException, IOException {

		try {

			if (tagBody == null || printerIdentifier.isEmpty())
				throw new InvalidParameterException("O Parametro não pode ser nulo.");

			PrintService printer = PrinterTagConfig.detectPrinter(printerIdentifier);

			var factory = new TemplateTagFactory();
			TagTemplateBase template = factory.getTemplate(tagBody, templateType);

			try (InputStream input = new ByteArrayInputStream(template.getBytes())) {
				PrinterTagConfig.printOut(printer, input);
			}

			return true;

		} catch (PrintException ex) {
			throw new PrinterException(ex.getMessage());
		}
	}
}
