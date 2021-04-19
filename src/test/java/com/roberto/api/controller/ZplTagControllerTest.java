package com.roberto.api.controller;

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
import com.roberto.api.controller.ZplTagController;
import com.roberto.api.controller.dto.PrintZplTagRequest;
import com.roberto.api.enums.TemplateTagType;
import com.roberto.api.service.PrinterService;

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
	public void printZplTagSuccessTest() throws Exception {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setCodeZpl("^XA^CFA,30^FO10,10^FDJohn Doe^FS^XZ");
		request.setPrinterName("ZPL");

		when(service.printTags("ZPL", request.getCodeZpl(), TemplateTagType.ZPLTAG)).thenReturn(true);

		mockMvc.perform(post("/printer/zpl/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isOk());
	}
	
	@Test
	public void printZplTagBadRequestTest() throws Exception {

		PrintZplTagRequest request = new PrintZplTagRequest();
		request.setCodeZpl(null);
		request.setPrinterName("");

		when(service.printTags("", request.getCodeZpl(), TemplateTagType.ZPLTAG)).thenReturn(false);

		mockMvc.perform(post("/printer/zpl/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isBadRequest());
	}	

	@Test
	public void printZplTagExceptionTest() throws Exception {

		PrintZplTagRequest request = new PrintZplTagRequest();		

		when(service.printTags("", request.getCodeZpl(), TemplateTagType.ZPLTAG));

		mockMvc.perform(post("/printer/zpl/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().is4xxClientError());		
		
	}
}
