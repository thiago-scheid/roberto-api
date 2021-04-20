package com.roberto.api.service;

import static org.junit.Assert.assertNotNull;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

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

	/*
	 * @Test(expected = PrinterNotFoundException.class) public void
	 * detectPrinterNotFoundTest() {
	 * 
	 * Mockito.mock(PrinterTagConfig.class); Mockito.mock(PrintServiceLookup.class);
	 * 
	 * service.detectPrinter("Zebra1"); }
	 * 
	 * @Test(expected = PrinterNotFoundException.class) public void
	 * printTagsNotFoundTest() throws PrinterException, IOException {
	 * 
	 * PrintZplTagRequest request = new PrintZplTagRequest();
	 * request.setCodeZpl("38eu38u"); request.setPrinterName("Zebra1");
	 * 
	 * Mockito.mock(PrinterTagConfig.class); Mockito.mock(PrintServiceLookup.class);
	 * 
	 * boolean result = service.printTags("ZPL", request.getCodeZpl(),
	 * TemplateTagType.ZPLTAG);
	 * 
	 * assertTrue(result); }
	 * 
	 * @Test(expected = InvalidParameterException.class) public void
	 * printTagsInvalidPrinterNameTest() throws PrinterException, IOException {
	 * 
	 * service.printTags("", new PrintZplTagRequest(), TemplateTagType.ZPLTAG); }
	 * 
	 * @Test(expected = InvalidParameterException.class) public void
	 * printTagsInvalidTagBodyTest() throws PrinterException, IOException {
	 * 
	 * service.printTags("Zebra1", null, TemplateTagType.ZPLTAG); }
	 * 
	 * @Test(expected = PrintException .class) public void printTagsExceptionTest()
	 * throws PrinterException, IOException {
	 * 
	 * service.printTags("Zebra1", new PrintZplTagRequest(), null); }
	 */
}
