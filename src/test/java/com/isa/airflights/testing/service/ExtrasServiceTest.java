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
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.model.RoomResExtras;
import com.isa.airflights.repository.ExtrasRepository;
import com.isa.airflights.repository.RoomResExtrasRepo;
import com.isa.airflights.service.ExtrasService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtrasServiceTest {
	
	@Mock
	private ExtrasRepository rep;
	
	@Mock
	private HotelExtras h;
	
	@Mock
	private RoomResExtras rre;
	
	@Mock
	private RoomResExtrasRepo rrx;
	
	@InjectMocks
	private ExtrasService ser;
	
	@Test
	public void testGetAll() {
		when(rep.findAll()).thenReturn(Arrays.asList(new HotelExtras(1L, "Bazen", 4.5)));
		List<HotelExtras> hotels = (List<HotelExtras>)ser.getAll();
		
		assertTrue(hotels.size()==1);
		
		verify(rep, times(1)).findAll();
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testGetOne() {
		when(rep.getOne(1L)).thenReturn(h);
		HotelExtras ex = ser.getOne(1L);
		assertEquals(h, ex);
		
		verify(rep, times(1)).getOne(1L);
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testSave() {
		when(rep.findAll()).thenReturn(Arrays.asList(new HotelExtras(1L, "Bazen", 4.5)));
		HotelExtras he = new HotelExtras(2L, "Bar", 3.5);
		
		when(rep.save(he)).thenReturn(he);
		
		int size1 = ser.getAll().size();
		
		HotelExtras sa = ser.save(he);
		
		assertThat(sa).isNotNull();
		
		when(rep.findAll()).thenReturn(Arrays.asList(new HotelExtras(1L, "Bazen", 4.5), sa));
		
		List<HotelExtras> list = (List<HotelExtras>)ser.getAll();
		
		sa = list.get(size1 - 1);
		
		assertThat(sa.getName()).isEqualTo("Bazen");
		assertThat(sa.getPrice()).isEqualTo(4.5);
		
		verify(rep, times(2)).findAll();
		verify(rep).save(he);
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testSave2() {
		when(rep.save(h)).thenReturn(h);
		HotelExtras he = ser.save(h);
		assertThat(he, is(equalTo(h)));
	}
	
	@Test
	public void testUpdate() {
		when(rep.save(h)).thenReturn(h);
		HotelExtras a = ser.update(h);
		assertThat(a, is(equalTo(h)));
	}
	
	@Test
	public void testsaveE() {
		when(rrx.save(rre)).thenReturn(rre);
		RoomResExtras c = ser.saveResExtra(rre);
		assertThat(c, is(equalTo(rre)));
	}
	
	@Test
	public void testroomres() {
		when(rep.findByHotel_id(1L)).thenReturn(Arrays.asList(new HotelExtras(1L, "Bazen", 4.5)));
		List<HotelExtras> e = ser.getExtrasByHotel(1L);
		
		assertTrue(e.size()==1);
		
		verify(rep, times(1)).findByHotel_id(1L);
		verifyNoMoreInteractions(rep);
	}
	
	
}
