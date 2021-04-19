package com.roberto.api.service;

import static org.junit.Assert.assertNotNull;
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
}
