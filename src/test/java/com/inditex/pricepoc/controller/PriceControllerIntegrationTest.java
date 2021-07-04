package com.inditex.pricepoc.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class PriceControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	private final static String PATH = "/price";

	private final static  String BRANDID = "1";

	private final static String PRODUCTID = "35455";
	
	@Test
	public void notFoundTest() throws Exception {
		
		String applicationDate = "2019-06-14 10:00:00";
		
		MvcResult result = requestPrice(applicationDate)
				.andExpect(status().isNotFound())
				.andReturn();

		String resultMvc = result.getResponse().getContentAsString();
		assertTrue(StringUtils.isEmpty(resultMvc));

	}

	
	@Test
	public void unprocessableEntityTest() throws Exception {
		
		String applicationDate = "2019-06-14";
		
		MvcResult result = requestPrice(applicationDate)
				.andExpect(status().isUnprocessableEntity())
				.andReturn();
		
		String resultMvc = result.getResponse().getContentAsString();
		assertNotNull(resultMvc);
		assertTrue(resultMvc.contains("applicationDate"));
	}
	
	
	@Test
	public void badRequestTest() throws Exception {
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
				.param("brandId", BRANDID)
				.param("productId", PRODUCTID)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn();

		String resultMvc = result.getResponse().getContentAsString();
		assertNotNull(resultMvc);
		assertTrue(resultMvc.contains("applicationDate"));

	}
		
	
	@Test
	public void priceTest1() throws Exception {
		
		String applicationDate = "2020-06-14 10:00:00";
		
		requestPrice(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value("35.5"));
	}
	

	@Test
	public void priceTest2() throws Exception {

		String applicationDate = "2020-06-14 16:00:00";
		
		requestPrice(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value("25.45"));
	}
	
	@Test
	public void priceTest3() throws Exception {

		String applicationDate = "2020-06-14 21:00:00";
		
		requestPrice(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value("35.5"));
	}
	
	@Test
	public void priceTest4() throws Exception {

		String applicationDate = "2020-06-15 10:00:00";
		
		requestPrice(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value("30.5"));
	}
	
	@Test
	public void priceTest5() throws Exception {

		String applicationDate = "2020-06-16 21:00:00";
		
		requestPrice(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value("38.95"));
	}
	
	private ResultActions requestPrice(String applicationDate) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders.get(PATH)
				.param("brandId", BRANDID)
				.param("productId", PRODUCTID)
				.param("applicationDate", applicationDate)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
	}
	
}
