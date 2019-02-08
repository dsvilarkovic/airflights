package com.isa.airflights.testing.service;

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

}
