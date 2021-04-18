package com.roberto.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberto.controller.ZplTagController;
import com.roberto.controller.dto.PrintZplTagRequest;
import com.roberto.enums.TemplateTagType;
import com.roberto.service.PrinterService;

@RunWith(SpringRunner.class)
public class ZplTagControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private PrinterService service;

	@InjectMocks
	private ZplTagController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void printZplTagBadRequestTest() throws Exception {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setZpl(null);
		request.setPrinterName("");

		when(service.printTags("", request.getZpl(), TemplateTagType.ZplTag)).thenReturn(false);

		mockMvc.perform(post("/printer/zpl/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isBadRequest());
	}

	@Test
	public void printZplTagSuccessTest() throws Exception {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setZpl("^XA^CFA,30^FO10,10^FDJohn Doe^FS^XZ");
		request.setPrinterName("ZPL");

		when(service.printTags("ZPL", request.getZpl(), TemplateTagType.ZplTag)).thenReturn(true);

		mockMvc.perform(post("/printer/zpl/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isOk());
	}
}
