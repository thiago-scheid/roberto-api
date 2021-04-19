package com.roberto.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.api.controller.dto.PrinterResponse;
import com.roberto.api.controller.dto.SystemPrinter;
import com.roberto.api.service.PrinterService;

@RunWith(SpringRunner.class)
public class PrinterControllerTest {

	@MockBean
	private PrinterService service;

	@InjectMocks
	private PrinterController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);		
	}

	@After
	public void tearDown() throws Exception {
	}

	private List<SystemPrinter> Prints() {

		List<SystemPrinter> list = new ArrayList<SystemPrinter>();

		for (int i = 0; i < 5; i++) {

			SystemPrinter print = new SystemPrinter();
			print.setId(null);
			print.setName("Zebra" + i++);
			list.add(print);
		}

		return list;
	}

	@Test
	public void printServerSucessTest() {

		when(service.getPrinterServer()).thenReturn(Prints());

		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints();

		assertEquals(response.toString(), 200, response.getStatusCodeValue());
	}

	@Test
	public void printServerExceptionTest() {
		
		when(service.getPrinterServer());

		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints();

		assertEquals(response.toString(), 500, response.getStatusCodeValue());
	}
}
