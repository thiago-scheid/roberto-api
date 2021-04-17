package com.roberto.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.service.PrinterService;
import com.roberto.service.PrinterServiceImpl;

@RunWith(SpringRunner.class)
public class PrinterServiceTest {

	@Spy
	@InjectMocks
	private PrinterService service = new PrinterServiceImpl();

	@Test
	public void shouldNotNullService() {
		Assert.assertNotNull(this.service);
	}
}
