package com.isa.airflights.testing.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

import com.isa.airflights.model.PromoRoom;
import com.isa.airflights.repository.PromoRoomRepository;
import com.isa.airflights.service.PromoRoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoRoomServiceTest {

	@Mock
	private PromoRoomRepository rep;
	
	@Mock
	private PromoRoom pr;
	
	@InjectMocks
	private PromoRoomService ser;
	
	@Test
	public void testSave() {
		when(rep.save(pr)).thenReturn(pr);
		PromoRoom he = ser.save(pr);
		assertThat(he, is(equalTo(pr)));
	}
	
	@Test
	public void testDelete() {
		when(rep.deleteByRoom_id(1L)).thenReturn(Arrays.asList(pr));
		List<PromoRoom> he = ser.deletyByRoom(1L);
		assertThat(he, is(equalTo(Arrays.asList(pr))));
	}
	
	@Test
	public void testGet() {
		when(rep.findByRoom_id(1L)).thenReturn(Arrays.asList());
		List<PromoRoom> e = ser.getByRoom(1L);
		
		assertTrue(e.size()==0);
		
		verify(rep, times(1)).findByRoom_id(1L);
		verifyNoMoreInteractions(rep);
	}
	
}
