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
import com.isa.airflights.model.PromoRoom;
import com.isa.airflights.model.Room;
import com.isa.airflights.testing.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomControllerTest {
	
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
	public void testgetRoom() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + 1002)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(1002));
		
	}
	
	public void testup() throws Exception {
		Room room = new Room();
		room.setId(1001L);
		room.setBalcony(true);
		room.setBeds(2);
		room.setDiscount(null);
		room.setFloor(1);
		room.setNumber(109);
		room.setRooms(2);
		Hotel h =  new Hotel();
		h.setId(1001L);
		h.setCity("");
		h.setName("1213");
		h.setAddress("");
		h.setDescription("");
		h.setRatingsCount(10L);
		h.setRatingsSum(43L);
		h.setVersion(0L);
		room.setHotel(h);
		room.setPrice(10.0);
		room.setPromo(false);
		room.setRatingsCount(10L);
		room.setRatingsSum(40L);
		room.setDiscount(10.0);
	
		
		String json = TestUtil.json(room);
		
		mockMvc.perform(put(URL_PREFIX + "/").contentType(contentType).content(json)).andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(1001))
		.andExpect(jsonPath("$.number").value(109));
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/hotel/" + 1002)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1001)));
	}
	
	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + 1001)).andExpect(status().isCreated());
	}
	
	@Test
	public void testPromo() throws Exception {
		PromoRoom pr = new PromoRoom();
		pr.setExtra(new HotelExtras());
		pr.setId(1L);
		pr.setRoom(new Room());
		
		String json = TestUtil.json(pr);
		
		this.mockMvc.perform(post(URL_PREFIX + "/promoExtra").contentType(contentType).content(json)).andExpect(status().isMethodNotAllowed());
		
	}
	
	@Test
	public void testcleanPromo() throws Exception {
		
		this.mockMvc.perform(put(URL_PREFIX + "/cleanPR/" + 1)).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void testget2() throws Exception {
		// Exception
		this.mockMvc.perform(get(URL_PREFIX + "/isReserved/" + 1005)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testgetAll() throws Exception {
		// Exception
		this.mockMvc.perform(get(URL_PREFIX + "/allReservation/" + 3)).andExpect(status().isNotFound());
	}
	
	

}
