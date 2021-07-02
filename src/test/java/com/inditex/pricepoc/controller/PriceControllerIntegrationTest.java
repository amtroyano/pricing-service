package com.inditex.pricepoc.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.inditex.pricepoc.service.PriceService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { PriceController.class})
@WebMvcTest
public class PriceControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PriceService priceService;

	private final String BRANDID = "1";

	private final String PRODUCTID = "35455";

	@Test
	public void getValuesTest() throws Exception {
		testPrice("2020-06-14 10:00:00");
		testPrice("2020-06-14 16:00:00");
		testPrice("2020-06-14 21:00:00");
		testPrice("2020-06-15 10:00:00");
		testPrice("2020-06-16 21:00:00");
	}

	private void testPrice(String fechaAplicacion) throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/").param("brandId", BRANDID)
				.param("productId", PRODUCTID).param("fechaAplicacion", fechaAplicacion)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String resultMvc = result.getResponse().getContentAsString();
		assertNotNull(resultMvc);
	}
}
