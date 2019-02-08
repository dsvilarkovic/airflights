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

import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.model.ReservationPackage;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.repository.ReservationPackageRepository;
import com.isa.airflights.repository.RoomReservationRepository;
import com.isa.airflights.service.ExtrasService;
import com.isa.airflights.service.RoomReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomReservationServiceTest {
	
	@Mock
	private RoomReservationRepository repository;
	
	@Mock
	private ReservationPackageRepository packageRepo;
	
	@Mock
	private ReservationPackage p;
	
	@Mock
	private ExtrasService xS;
	
	@Mock
	private RoomReservation rr;
	
	@Mock
	private List<HotelExtras> extrasRealList;
	
	@InjectMocks
	private RoomReservationService ser;
	
	@Test
	public void getAllTest() {
		when(repository.findAll()).thenReturn(Arrays.asList(new RoomReservation(), new RoomReservation()));
		List<RoomReservation> ress = ser.getAll();
		
		assertTrue(ress.size()==2);
		
		verify(repository, times(1)).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	public void getByRoomTest() {
		when(repository.findByRoom_id(1L)).thenReturn(Arrays.asList(new RoomReservation()));
		List<RoomReservation> e = ser.getByRoom(1L);
		
		assertTrue(e.size()==1);
		
		verify(repository, times(1)).findByRoom_id(1L);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void testGetOne() {
		when(packageRepo.getOne(1L)).thenReturn(p);
		ReservationPackage rp = ser.getRP(1L);
		
		assertThat(rp, is(equalTo(p)));
		
		verify(packageRepo, times(1)).getOne(1L);
		verifyNoMoreInteractions(packageRepo);
	}
	
	@Test
	public void testSave() {
		when(repository.save(rr)).thenReturn(rr);
		RoomReservation a = ser.save(rr);
		assertThat(a, is(equalTo(rr)));
	}
	
	@Test
	public void findAllTest() {
		when(repository.findAll()).thenReturn(Arrays.asList(new RoomReservation(), new RoomReservation()));
		List<RoomReservation> ress = ser.getAll();
		
		assertTrue(ress.size()==2);
		
		verify(repository, times(1)).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void testSaveFull() {
		when(repository.save(rr)).thenReturn(rr);
		RoomReservation a = ser.save(rr);
		assertThat(a, is(equalTo(rr)));
	}

}
