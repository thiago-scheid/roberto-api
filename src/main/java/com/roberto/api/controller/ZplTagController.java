package com.roberto.api.controller;

import com.roberto.api.controller.dto.PrintZplTagRequest;
import com.roberto.api.controller.dto.PrintZplTagResponse;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.service.PrinterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/printer/zpl/")
public class ZplTagController {

	@Autowired
	private PrinterService service;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Imprime etiqueta, passando o código ZPL")
	public ResponseEntity<PrintZplTagResponse> print(@RequestBody PrintZplTagRequest request) {

		PrintZplTagResponse response = new PrintZplTagResponse();

		try {

			response.setSystem("roberto");
			response.setStatus(false);

			boolean success = service.printTags(request.getPrinterName(), request.getZpl(), TemplateTagType.ZPLTAG);

			if (success) {

				response.setMessage("Sucesso ao imprimir etiqueta.");
				response.setStatus(true);

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {

				response.setMessage("Erro ao imprimir etiqueta.");

				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());

			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}