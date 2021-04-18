package com.roberto.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import com.roberto.api.service.PrinterService;
import com.roberto.api.service.PrinterServiceImpl;

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
