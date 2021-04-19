package com.roberto.api.controller;

import java.util.List;
import javax.print.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.roberto.api.controller.dto.PrinterResponse;
import com.roberto.api.controller.dto.SystemPrinter;
import com.roberto.api.exception.PrinterNotFoundException;
import com.roberto.api.service.PrinterService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/printers/")
public class PrinterController {

	@Autowired
	private PrinterService service;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista as impressoras instaladas no servidor", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> prints() {

		PrinterResponse response = new PrinterResponse();

		try {
			
			response.setSystem("roberto");
			response.setStatus(false);

			List<SystemPrinter> list = service.getPrinterServer();

			response.setPrinters(list);
			response.setStatus(true);

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "{printerName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Verifica a impressora instalada no servidor pelo nome", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> printDetect(@PathVariable("printerName") String printerName) {

		PrinterResponse response = new PrinterResponse();

		try {

			response.setSystem("roberto");			

			PrintService printer = service.detectPrinter(printerName);

			if (printer == null) {
				response.setStatus(false);
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
			
			response.setMessage("Impressora - OK");
			response.setStatus(true);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (PrinterNotFoundException ex) {

			response.setMessage("Impressora não instalada no servidor.");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
