package com.isa.airflights.testing.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.isa.airflights.testing.constants.RentACarConstants.DB_ADDRESS;
import static com.isa.airflights.testing.constants.RentACarConstants.DB_COUNT;
import static com.isa.airflights.testing.constants.RentACarConstants.DB_NAME;
import static com.isa.airflights.testing.constants.RentACarConstants.DB_DESCRIPTION;


import java.nio.charset.Charset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.isa.airflights.testing.constants.RentACarConstants;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarControllerTest {
	
	private static final String URL_PREFIX = "/api/rentacar";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}
	
	@Test
	public void testGetAllRentacars() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/test")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
		.andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
		.andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)));

	}
	
	@Test
	public void testGetRentacar() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + RentACarConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(RentACarConstants.DB_ID.intValue()))
		.andExpect(jsonPath("$.name").value(DB_NAME))
		.andExpect(jsonPath("$.description").value(DB_DESCRIPTION));
	}
	
	@Test
	public void testSearch() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/search/" + RentACarConstants.DB_NAME)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(RentACarConstants.DB_COUNT_WITH_DB_NAME)));
	}
	
	@Test
	public void testSearchByCity() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/search/" + RentACarConstants.DB_CITY)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(RentACarConstants.DB_COUNT_WITH_DB_CITY)));
	}
	
	

}
