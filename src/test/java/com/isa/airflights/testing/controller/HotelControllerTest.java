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
import com.isa.airflights.model.SearchObject;
import com.isa.airflights.testing.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerTest {
	
	private static final String URL_PREFIX = "/api/hotel";
	
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
	public void testGetAll() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/list")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].name").value(hasItem("Sava")))
		.andExpect(jsonPath("$.[*].city").value(hasItem("Nis")))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1001)))
		.andExpect(jsonPath("$.[*].address").value(hasItem("Vrsacka 10")));
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 1002)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name").value("Garni"))
		.andExpect(jsonPath("$.city").value("Novi Sad"))
		.andExpect(jsonPath("$.id").value(1002))
		.andExpect(jsonPath("$.ratingsCount").value(13))
		.andExpect(jsonPath("$.ratingsSum").value(42));
	}
	
	@Test
	public void testGetFail() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 55)).andExpect(status().isInternalServerError());
		
	}
	
	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + 1003)).andExpect(status().isOk());
	}
	
	@Test
	public void testAdd() throws Exception {
		
		Hotel h = new Hotel();
		h.setAddress("Bulevar Mihajla Pupina");
		h.setCity("Beograd");
		h.setDescription("Tradicionalni hotel");
		h.setName("Pupin");
		h.setRatingsCount(15L);
		h.setRatingsSum(61L);
		h.setId(91L);
		
		String json = TestUtil.json(h);
		
		this.mockMvc.perform(post(URL_PREFIX + "/add").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Hotel h = new Hotel();
		h.setAddress("Bulevar Mihajla Pupina");
		h.setCity("Beograd");
		h.setDescription("Tradicionalni hotel");
		h.setName("Pupin");
		h.setRatingsCount(15L);
		h.setRatingsSum(61L);
		h.setId(1001L);
		
		String json = TestUtil.json(h);
		
		this.mockMvc.perform(put(URL_PREFIX + "/").contentType(contentType).content(json)).andExpect(status().isCreated());
	
	}
	
	@Test
	public void testUpdateFail() throws Exception {
		Hotel h = new Hotel();
		h.setAddress("Bulevar Mihajla Pupina");
		h.setCity("Beograd");
		h.setDescription("Tradicionalni hotel");
		h.setName("Pupin");
		h.setRatingsCount(15L);
		h.setRatingsSum(61L);
		h.setId(1021L);
		
		String json = TestUtil.json(h);
		
		this.mockMvc.perform(put(URL_PREFIX + "/").contentType(contentType).content(json)).andExpect(status().isCreated());
	
	}
	
	@Test
	public void testget() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 1002 + "/rooms").contentType(contentType)).andExpect(status().isOk());
	}
	
	@Test
	public void testSearch() throws Exception {
		
		SearchObject obj = new SearchObject();
		
		obj.setStartD(12);
		obj.setStartM(2);
		obj.setStartY(2019);
		
		obj.setEndD(19);
		obj.setEndM(2);
		obj.setEndY(2019);
		
		obj.setName("Random");
		obj.setLocation("Home");
		
		String json = TestUtil.json(obj);
		
		this.mockMvc.perform(post(URL_PREFIX + "/search").contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void revenues() throws Exception {
		
		SearchObject obj = new SearchObject();
		
		obj.setStartD(12);
		obj.setStartM(2);
		obj.setStartY(2019);
		
		obj.setEndD(19);
		obj.setEndM(2);
		obj.setEndY(2019);
		
		String json = TestUtil.json(obj);
		
		this.mockMvc.perform(post(URL_PREFIX + "/revenues/" +  1001).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	public void wc() throws Exception {
		
		this.mockMvc.perform(get(URL_PREFIX + "/chartWeek/" +  1001)).andExpect(status().isOk());
		
	}
	
	public void mc() throws Exception {
		
		this.mockMvc.perform(post(URL_PREFIX + "/lastM/" +  1001)).andExpect(status().isOk());
		
	}
	
	public void yc() throws Exception {
		
		this.mockMvc.perform(post(URL_PREFIX + "/lastYear/" +  1001).contentType(contentType)).andExpect(status().isOk());
		
	}
	
	
}
