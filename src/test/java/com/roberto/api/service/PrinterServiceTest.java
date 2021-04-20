package com.roberto.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.security.InvalidParameterException;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.api.controller.dto.PrintZplTagRequest;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.exception.PrinterException;
import com.roberto.api.exception.PrinterNotFoundException;

@RunWith(SpringRunner.class)
public class PrinterServiceTest {

	@Spy
	@InjectMocks
	private PrinterService service = new PrinterServiceImpl();

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		PrintService psDefault = mock(PrintService.class);
		when(psDefault.getName()).thenReturn("DefaultPrinter");
		when(psDefault.isDocFlavorSupported(any(DocFlavor.class))).thenReturn(Boolean.TRUE);
		PrintServiceLookup psLookup = mock(PrintServiceLookup.class);
		when(psLookup.getPrintServices()).thenReturn(new PrintService[] { psDefault });
		when(psLookup.getDefaultPrintService()).thenReturn(psDefault);
		DocPrintJob docPrintJob = mock(DocPrintJob.class);
		when(psDefault.createPrintJob()).thenReturn(docPrintJob);
		MediaTray[] trays = new MediaTray[] { MediaTray.TOP, MediaTray.MIDDLE, MediaTray.BOTTOM };
		when(psDefault.getSupportedAttributeValues(Media.class, null, null)).thenReturn(trays);
		PrintServiceLookup.registerServiceProvider(psLookup);
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

		var list = service.getPrinterServer();

		assertNotNull(list);
	}

	@Test
	public void detectPrinterTest() {

		service.detectPrinter("DefaultPrinter");
	}

	@Test(expected = PrinterNotFoundException.class)
	public void detectPrinterNotFoundTest() {

		service.detectPrinter("Zebra1");
	}

	@Test
	public void printTagsSucessTest() throws PrinterException, IOException {

		boolean result = service.printTags("DefaultPrinter",
				"^XA^FX Third section with bar code.^BY5,2,270^FO100,550^BC^FD12345678^FS^XZ", TemplateTagType.ZPLTAG);

		assertTrue(result);
	}

	@Test(expected = InvalidParameterException.class)
	public void printTagsInvalidPrinterNameTest() throws PrinterException, IOException {

		service.printTags("", new PrintZplTagRequest(), TemplateTagType.ZPLTAG);
	}

	@Test(expected = InvalidParameterException.class)
	public void printTagsInvalidTagBodyTest() throws PrinterException, IOException {

		service.printTags("Zebra1", null, TemplateTagType.ZPLTAG);
	}

	@Test(expected = NullPointerException.class)
	public void printTagsExceptionTest() throws PrinterException, IOException {

		service.printTags("DefaultPrinter", new PrintZplTagRequest(), null);
	}
}
