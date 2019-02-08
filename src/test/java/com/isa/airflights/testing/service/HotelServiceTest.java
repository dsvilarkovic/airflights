package com.isa.airflights.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.SearchObject;
import com.isa.airflights.repository.HotelRepository;
import com.isa.airflights.service.HotelService;
import com.isa.airflights.testing.constants.HotelConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {
	
	@Mock
	private HotelRepository hr;
	
	@Mock
	private Hotel h;
	
	@Mock
	private SearchObject obj;
	
	@InjectMocks
	private HotelService hs;
	
	@Test
	public void testGetAll() {
		when(hr.findAll()).thenReturn(Arrays.asList(new Hotel(HotelConstants.DB_NAME, HotelConstants.DB_ADDRESS, HotelConstants.DB_DESC, HotelConstants.DB_CITY, HotelConstants.DB_COUNT, HotelConstants.DB_SUM)));
		Collection<Hotel> hotels = hs.getAll();
		
		assertTrue(hotels.size()==1);
		
		verify(hr, times(1)).findAll();
		verifyNoMoreInteractions(hr);
	}
	
	@Test
	public void testGetOne() {
		when(hr.getOne(HotelConstants.DB_ID)).thenReturn(h);
		Hotel hotel = hs.getOne(HotelConstants.DB_ID);
		assertEquals(h, hotel);
		
		verify(hr, times(1)).getOne(HotelConstants.DB_ID);
		verifyNoMoreInteractions(hr);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	
	public void testSave() {
		when(hr.findAll()).thenReturn(Arrays.asList(new Hotel(HotelConstants.DB_NAME, HotelConstants.DB_ADDRESS, HotelConstants.DB_DESC, HotelConstants.DB_CITY, HotelConstants.DB_COUNT, HotelConstants.DB_SUM)));
		Hotel hotel = new Hotel();
		hotel.setName(HotelConstants.DB_NAME_NEW);
		hotel.setAddress(HotelConstants.DB_ADDRESS_NEW);
		hotel.setDescription(HotelConstants.DB_DESC_NEW) ;
		hotel.setCity(HotelConstants.DB_CITY_NEW) ;
		hotel.setRatingsCount(HotelConstants.DB_COUNT_NEW);
		hotel.setRatingsSum(HotelConstants.DB_SUM_NEW);
		
		when(hr.save(hotel)).thenReturn(hotel);
		
		int size1 = hs.getAll().size();
		
		Hotel hdb = hs.save(hotel);
		
		assertThat(hdb).isNotNull();
		
		when(hr.findAll()).thenReturn(Arrays.asList(new Hotel(HotelConstants.DB_NAME, HotelConstants.DB_ADDRESS, HotelConstants.DB_DESC, HotelConstants.DB_CITY, HotelConstants.DB_COUNT, HotelConstants.DB_SUM), hotel));
		
		List<Hotel> hotels = (List<Hotel>)hs.getAll();
		
		assertThat(hotels).hasSize(size1 + 1 );
		
		hdb = hotels.get(hotels.size() - 1);
		assertThat(hdb.getName()).isEqualTo(HotelConstants.DB_NAME_NEW);
		assertThat(hdb.getAddress()).isEqualTo(HotelConstants.DB_ADDRESS_NEW);
		assertThat(hdb.getDescription()).isEqualTo(HotelConstants.DB_DESC_NEW);
		assertThat(hdb.getCity()).isEqualTo(HotelConstants.DB_CITY_NEW);
		assertThat(hdb.getRatingsCount()).isEqualTo(HotelConstants.DB_COUNT_NEW);
		assertThat(hdb.getRatingsSum()).isEqualTo(HotelConstants.DB_SUM_NEW);
		
		verify(hr, times(2)).findAll();
		verify(hr, times(1)).save(hotel);
		verifyNoMoreInteractions(hr);
		
		
	}
	
	@Test
	public void testSave2() {
		when(hr.save(h)).thenReturn(h);
		Hotel a = hs.save(h);
		assertThat(a, is(equalTo(h)));
	}
	
	@Test
	public void testupdate() {
		when(hr.save(h)).thenReturn(h);
		Hotel a = hs.update(h);
		assertThat(a, is(equalTo(h)));
	}
	
	@Test
	public void filter() {
		when(hr.findAll()).thenReturn(Arrays.asList(new Hotel(HotelConstants.DB_NAME, HotelConstants.DB_ADDRESS, HotelConstants.DB_DESC, HotelConstants.DB_CITY, HotelConstants.DB_COUNT, HotelConstants.DB_SUM)));
		when(obj.getName()).thenReturn("h");
		when(obj.getLocation()).thenReturn("n");
		
		List<Hotel> f = hs.getFiltered(obj);
		
		assertThat(f).hasSize(1);
		
	}

}
