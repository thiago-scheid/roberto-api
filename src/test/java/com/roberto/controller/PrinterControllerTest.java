package com.roberto.controller;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.controller.PrinterController;
import com.roberto.controller.dto.PrinterResponse;
import com.roberto.controller.dto.SystemPrinter;
import com.roberto.service.PrinterService;

@RunWith(SpringRunner.class)
public class PrinterControllerTest {

	@MockBean
	private PrinterService service;

	private List<SystemPrinter> GeneratePrint(String mode) {

		List<SystemPrinter> list = new ArrayList<SystemPrinter>();

		for (int i = 0; i < 5; i++) {

			// Servidor
			if (mode == "S") {

				SystemPrinter print = new SystemPrinter();
				print.setId(null);
				print.setName("Zebra" + i++);
				list.add(print);
			}
			// Database
			else {

				SystemPrinter print = new SystemPrinter();
				print.setId(1L + i);
				print.setName("Zebra" + i++);
				list.add(print);
			}
		}

		return list;
	}

	@Test
	public void printServerTestSuccess() {

		Mockito.when(service.getPrinterServer()).thenReturn(GeneratePrint("S"));

		PrinterController controller = new PrinterController(service);

		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints();

		assertEquals(response.toString(), 200, response.getStatusCodeValue());
	}
}
