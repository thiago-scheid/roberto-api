package com.roberto.controller;

import com.roberto.controller.dto.PrinterResponse;
import com.roberto.controller.dto.SystemPrinter;
import com.roberto.exception.PrinterNotFoundException;
import com.roberto.service.PrinterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.print.PrintService;
import java.util.List;

@RestController
public class PrinterController {

	@Autowired
	private PrinterService service;

	public PrinterController() {
	}

	public PrinterController(PrinterService service) {
		this.service = service;
	}

	@GetMapping(value = "/printers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista de impressoras cadastradas no servidor", notes = "Lista de impressoras cadastradas no servidor", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> prints() {

		PrinterResponse response = new PrinterResponse();

		response.setSystem("roberto");

		try {

			List<SystemPrinter> list = service.getPrinterServer();

			response.setPrinters(list);

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/printers/{printerName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Verifica a impressora cadastrada no servidor pelo nome", notes = "Verifica a impressora cadastrada no servidor pelo nome", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> printDetect(@PathVariable("printerName") String printerName) {

		PrinterResponse response = new PrinterResponse();

		response.setStatus(false);

		try {
			PrintService printer = service.detectPrinter(printerName);

			if (printer == null) {
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			} else {
				response.setMessage("Impressora - OK");
				response.setStatus(true);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (PrinterNotFoundException ex) {
			
			response.setMessage("Impressora não instalada no servidor.");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
