package com.inditex.pricing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PricingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test1() throws Exception {
		request("2020-06-14T10:00:00.000").andExpect(status().isOk())
				.andExpect(jsonPath("$.price.amount").value("35.5"));
	}

	@Test
	void test2() throws Exception {
		request("2020-06-14T16:00:00.000").andExpect(status().isOk())
				.andExpect(jsonPath("$.price.amount").value("25.45"));
	}

	@Test
	void test3() throws Exception {
		request("2020-06-14T21:00:00.000").andExpect(status().isOk())
				.andExpect(jsonPath("$.price.amount").value("35.5"));
	}

	@Test
	void test4() throws Exception {
		request("2020-06-15T10:00:00.000").andExpect(status().isOk())
				.andExpect(jsonPath("$.price.amount").value("30.5"));
	}

	@Test
	void priceTest5() throws Exception {
		request("2020-06-16T21:00:00.000").andExpect(status().isOk())
				.andExpect(jsonPath("$.price.amount").value("38.95"));
	}

	@Test
	void notFoundTest() throws Exception {
		MvcResult result = request("2023-07-11T00:00:00.000").andExpect(status().isNotFound()).andReturn();

		assertEquals("Price not found", result.getResponse().getContentAsString());

	}

	private ResultActions request(String applicationDate) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders.get("/price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", applicationDate).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}
}
