package com.roberto.util;

import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import com.roberto.exception.PrinterNotFoundException;

public class PrinterTagConfig {

	private PrinterTagConfig() {

	}

	// Busca impressora
	public static PrintService detectPrinter(String printer) {

		PrintService print = null;
		DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
		for (PrintService p : ps) {
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
