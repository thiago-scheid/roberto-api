package com.roberto.controller;

import com.roberto.controller.dto.PrintZplTagRequest;
import com.roberto.controller.dto.PrintZplTagResponse;
import com.roberto.enums.TemplateTagType;
import com.roberto.service.PrinterService;
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
	@ApiOperation(value = "Imprimir etiqueta ZPL")
	public ResponseEntity<PrintZplTagResponse> print(@RequestBody PrintZplTagRequest request) {

		try {
			
			boolean success = service.printTags(request.getPrinterIdentifier(), request.getZpl(),
					TemplateTagType.ZplTag);

			if (success) {
				
				return new ResponseEntity<>(new PrintZplTagResponse("Sucesso ao imprimir etiqueta."), HttpStatus.OK);
			} else {
				
				return new ResponseEntity<>(new PrintZplTagResponse("Erro ao imprimir etiqueta."),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			return new ResponseEntity<>(new PrintZplTagResponse(ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}
}