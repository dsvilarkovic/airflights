package com.isa.airflights.testing.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleType;
import com.isa.airflights.testing.constants.VehicleConstants;
import com.isa.airflights.testing.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleControllerTest {
	
	private static final String URL_PREFIX = "/api/vehicle";
	
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
	public void testGetAllVehicles() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/test")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(VehicleConstants.VEHICLE_DB_COUNT)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(VehicleConstants.DB_VEHICLE_ID.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(VehicleConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].model").value(hasItem(VehicleConstants.DB_MODEL)))
		.andExpect(jsonPath("$.[*].brand").value(hasItem(VehicleConstants.DB_BRAND)))
		.andExpect(jsonPath("$.[*].year").value(hasItem(VehicleConstants.DB_YEARS)))
		.andExpect(jsonPath("$.[*].seats").value(hasItem(VehicleConstants.DB_SEATS)))
		.andExpect(jsonPath("$.[*].price").value(hasItem(VehicleConstants.DB_PRICE)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddVehicle() throws Exception {
		Vehicle v = new Vehicle();
		
		v.setModel(VehicleConstants.DB_MODEL_NEW);
		v.setBrand(VehicleConstants.DB_BRAND_NEW);
		v.setName(VehicleConstants.DB_NAME_NEW);
		v.setPrice(VehicleConstants.DB_PRICE_NEW);
		v.setYear(VehicleConstants.DB_YEARS_NEW);
		v.setRating(VehicleConstants.DB_RATING_NEW);
		v.setType(VehicleType.MEDIUM_CARS);
		v.setRating(4);
		v.setRatingsCount(1);
		v.setRatingsSum(4);
		v.setReserved(false);
		v.setSeats(VehicleConstants.DB_SEATS);
		

		String json = TestUtil.json(v);
		this.mockMvc.perform(put(URL_PREFIX+"/addVehicle/"+VehicleConstants.DB_RENTACAR_ID+"/"+VehicleConstants.DB_BRANCH_LOCATION_ID).contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void tesetDeleteVehicle() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/delete/" + VehicleConstants.DB_VEHICLE_ID)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateVehicle() throws Exception {
		Vehicle v = new Vehicle();
		
		v.setId(1L);
		v.setName(VehicleConstants.NEW_DB_NAME);
		

		String json = TestUtil.json(v);
		this.mockMvc.perform(put(URL_PREFIX+"/update").contentType(contentType).content(json)).andExpect(status().isOk());
	}

}
