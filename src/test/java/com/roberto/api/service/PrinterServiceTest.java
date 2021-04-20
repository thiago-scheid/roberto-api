package com.roberto.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.security.InvalidParameterException;
import javax.print.PrintServiceLookup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.api.controller.dto.PrintZplTagRequest;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.exception.PrinterException;
import com.roberto.api.exception.PrinterNotFoundException;
import com.roberto.api.util.PrinterTagConfig;

@RunWith(SpringRunner.class)
public class PrinterServiceTest {

	@Spy
	@InjectMocks
	private PrinterService service = new PrinterServiceImpl();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldNotNullService() {
		Assert.assertNotNull(this.service);
	}

	@Test
	public void getPrinterServerSucessTest() {

		Mockito.mock(PrintServiceLookup.class);

		var list = service.getPrinterServer();

		assertNotNull(list);
	}

	@Test(expected = PrinterNotFoundException.class)
	public void detectPrinterNotFoundTest() {

		Mockito.mock(PrinterTagConfig.class);
		Mockito.mock(PrintServiceLookup.class);

		service.detectPrinter("Zebra1");		
	}

	@Test(expected = PrinterNotFoundException.class)
	public void printTagsNotFoundTest() throws PrinterException, IOException {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setCodeZpl("38eu38u");
		request.setPrinterName("Zebra1");
				
		Mockito.mock(PrinterTagConfig.class);
		Mockito.mock(PrintServiceLookup.class);

		boolean result = service.printTags("ZPL", request.getCodeZpl(), TemplateTagType.ZPLTAG);

		assertTrue(result);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void printTagsInvalidParameterTest() throws PrinterException, IOException {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setCodeZpl(null);
		request.setPrinterName(null);
		
		service.printTags("ZPL", request.getCodeZpl(), TemplateTagType.ZPLTAG);		
	}
}
