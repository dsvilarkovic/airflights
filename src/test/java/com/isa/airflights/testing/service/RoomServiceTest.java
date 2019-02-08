package com.isa.airflights.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.isa.airflights.model.Room;
import com.isa.airflights.repository.RoomRepository;
import com.isa.airflights.service.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {
	
	@Mock
	private RoomRepository rep;
	
	@Mock
	private Room room;
	
	@InjectMocks
	private RoomService ser;
	
	@Test
	public void testGetAll() {
		when(rep.findAll()).thenReturn(Arrays.asList(new Room(1L, 101, 2, 1, 4, 4.5)));
		List<Room> e = ser.getAll();
		
		assertTrue(e.size()==1);
		
		verify(rep, times(1)).findAll();
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testGetOne() {
		when(rep.getOne(1L)).thenReturn(room);
		Room r = rep.getOne(1L);
		assertThat(r, is(equalTo(room)));
		
		verify(rep, times(1)).getOne(1L);
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testGetByHotel() {
		when(rep.findByHotel_id(1L)).thenReturn(Arrays.asList(new Room(1L, 101, 2, 1, 4, 4.5), new Room(1L, 101, 2, 1, 4, 4.5)));
		List<Room> e = ser.getRoomByHotel(1L);
		
		assertTrue(e.size()==2);
		
		verify(rep, times(1)).findByHotel_id(1L);
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testSave() {
		when(rep.findAll()).thenReturn(Arrays.asList(new Room(1L, 101, 2, 1, 4, 4.5), new Room(1L, 101, 2, 1, 4, 4.5)));
		Room room = new Room();
		room.setBeds(4);
		room.setBalcony(true);
		room.setFloor(1);
		room.setNumber(101);
		room.setDiscount(10.0);
		room.setRatingsCount(20L);
		room.setRatingsSum(50L);
		
		when(rep.save(room)).thenReturn(room);
		
		int size2 = ser.getAll().size();
		
		Room rdb = ser.save(room);
		
		assertThat(rdb).isNotNull();
		
		when(rep.findAll()).thenReturn(Arrays.asList(new Room(1L, 101, 2, 1, 4, 4.5), new Room(1L, 101, 2, 1, 4, 4.5), room));
		
		List<Room> rooms = (List<Room>)ser.getAll();
		
		assertThat(rooms).hasSize(size2 + 1 );
		
		rdb = rooms.get(rooms.size() - 1);
		assertThat(rdb.getBeds()).isEqualTo(4);
		assertThat(rdb.getFloor()).isEqualTo(1);
		assertThat(rdb.getDiscount()).isEqualTo(10.0);
		assertThat(rdb.getNumber()).isEqualTo(101);
		assertThat(rdb.getRatingsCount()).isEqualTo(20L);
		assertThat(rdb.getRatingsSum()).isEqualTo(50L);
		
		verify(rep, times(2)).findAll();
		verify(rep, times(1)).save(room);
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void testUpdate() {
		when(rep.save(room)).thenReturn(room);
		Room r = ser.update(room);
		assertThat(r, is(equalTo(room)));
	}
	
	@Test
	public void testsave() {
		when(rep.save(room)).thenReturn(room);
		Room r = ser.save(room);
		assertThat(r, is(equalTo(room)));
	}
	
	@Test
	public void testEndPromo() {
		when(rep.save(room)).thenReturn(room);
		Room r = ser.save(room);
		
		assertThat(r.getDiscount(), is(equalTo(0.0)));
		assertThat(r.getPromo(), is(equalTo(false)));
	}

}
