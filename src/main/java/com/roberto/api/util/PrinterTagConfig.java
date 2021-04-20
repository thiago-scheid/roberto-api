package com.roberto.api.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import com.roberto.api.controller.dto.SystemPrinter;
import com.roberto.api.exception.PrinterNotFoundException;

public class PrinterTagConfig {

	private PrinterTagConfig() {

	}
	
	// Lista impressoras instaladas no servidor
	public static List<SystemPrinter> getPrinterServer() {

		PrintService[] prints = null;		
		prints = PrintServiceLookup.lookupPrintServices(null, null);

		List<SystemPrinter> list = new ArrayList<>();

		for (PrintService ps : prints) {

			var print = new SystemPrinter();
			print.setId(null);
			print.setName(ps.getName());

			list.add(print);
		}

		return list;
	}

	// Busca impressora pelo nome
	public static PrintService detectPrinter(String printer) {

		PrintService print = null;		
		PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
		
		for (PrintService p : prints) {
			if (p.getName().equals(printer)) {
				print = p;
				break;
			}
		}

		if (print == null)
			throw new PrinterNotFoundException(printer);

		return print;
	}

	// Imprime
	public static boolean printOut(PrintService printservice, InputStream text) throws PrintException {

		DocPrintJob dpj = printservice.createPrintJob();
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc doc = new SimpleDoc(text, flavor, null);
		dpj.print(doc, null);

		return true;
	}
}
