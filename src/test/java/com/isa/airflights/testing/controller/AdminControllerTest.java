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

import com.isa.airflights.dto.UserDTODjuka;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.Misc;
import com.isa.airflights.testing.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
	
	private static final String URL_PREFIX = "/api/admin";
	
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
		mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].lastName").value(hasItem("Peric")))
		.andExpect(jsonPath("$.[*].firstName").value(hasItem("Pera")))
		.andExpect(jsonPath("$.[*].email").value(hasItem("nemanja@gmail.com")));
	}
	
	@Test
	public void testGetAllA() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/allA")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].lastName").value(hasItem("Peric")))
		.andExpect(jsonPath("$.[*].firstName").value(hasItem("Pera")))
		.andExpect(jsonPath("$.[*].email").value(hasItem("nemanja@gmail.com")));
	}
	
	@Test
	public void testAdd() throws Exception {
		AbstractUser au = new AbstractUser();
		au.setId(9L);
		au.setEmail("neko@gmail.com");
		au.setFirstName("Goran");
		au.setLastName("Goranovic");
		au.setIdRentACar(0);
		au.setAddress("Address");
		au.setAirline(new Airline());
		au.setChangePass(false);
		au.setFlightTickets(null);
		Hotel h = new Hotel();
		h.setId(9L);
		au.setHotel(h);
		
		//String json = TestUtil.json(au);
		
		//mockMvc.perform(post(URL_PREFIX + "/add").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 1)).andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.firstName").value("Nemanja"))
		.andExpect(jsonPath("$.lastName").value("Dimsic"))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.email").value("nemanja@gmail.com"));
	}
	
	@Test
	public void testpass() throws Exception {
		UserDTODjuka u = new UserDTODjuka();
		u.setId(1L);
		u.setNewPassword("123");
		
		String json = TestUtil.json(u);
		
		mockMvc.perform(put(URL_PREFIX + "/passUpdate").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	public void testDelete() throws Exception {
		//this.mockMvc.perform(delete(URL_PREFIX + "/" + 4)).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteRac() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/rac/" + 1)).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteAir() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/air/" + 1)).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateAdmin() throws Exception {
		AbstractUser u = new AbstractUser();
		u.setId(1L);
		u.setLastName("Peric");
		u.setFirstName("Pera");
		u.setIdRentACar(0);
		
		String json = TestUtil.json(u);
		
		this.mockMvc.perform(put(URL_PREFIX + "/updateAdmin" ).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testmisc() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/misc" )).andExpect(status().isOk());
		
	}
	
	@Test
	public void testMisc2() throws Exception {
		Misc m = new Misc();
		m.setB(1);
		m.setBb(2);
		m.setB3(3);
		m.setId(1L);
		
		String json = TestUtil.json(m);
		
		this.mockMvc.perform(post(URL_PREFIX + "/misc" ).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	
	@Test
	public void testmisc3() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/miscAll" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		
	}
	
	
	@Test
	public void testgetAL()  throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getAL" )).andExpect(status().isOk());
		
	}
	
	
	
}
