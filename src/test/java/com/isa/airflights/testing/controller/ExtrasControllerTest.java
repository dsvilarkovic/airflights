package com.isa.airflights.testing.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.model.HotelExtras.UnitPrice;
import com.isa.airflights.testing.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtrasControllerTest {

	private static final String URL_PREFIX = "/api/extras";
	
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
	public void testgetHotelE() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/hotel/" + 1002)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1001)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("WiFi")));
		
	}
	
	@Test
	public void testgetEinHotel() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 1001)).andExpect(status().isInternalServerError())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void testAddE() throws Exception{
		HotelExtras extra = new HotelExtras();
		extra.setId(10L);
		extra.setName("Rucak");
		extra.setPrice(10.0);
		extra.setUnit(UnitPrice.PER_DAY);
		Hotel h =  new Hotel();
		h.setId(1001L);
		h.setCity("");
		h.setName("");
		
		String json = TestUtil.json(extra);
		
		mockMvc.perform(post(URL_PREFIX + "/add").contentType(contentType).content(json)).andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("Rucak"));
	}
	
	@Test
	public void testdeleteE() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + 1001)).andExpect(status().isCreated());
	}
	
	@Test
	public void testupdateHotelE() throws Exception {
		HotelExtras extra = new HotelExtras();
		extra.setId(1001L);
		extra.setName("Rucak");
		extra.setPrice(10.0);
		extra.setUnit(UnitPrice.PER_DAY);
		Hotel h =  new Hotel();
		h.setId(1001L);
		h.setCity("");
		h.setName("");
		extra.setHotel(h);
		
		String json = TestUtil.json(extra);
		
		this.mockMvc.perform(put(URL_PREFIX + "/").contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	
}
