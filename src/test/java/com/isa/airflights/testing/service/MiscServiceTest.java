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
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.airflights.model.Misc;
import com.isa.airflights.repository.MiscRepository;
import com.isa.airflights.service.MiscService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiscServiceTest {

	@Mock
	private MiscRepository rep;
	
	@Mock
	private Misc m;
	
	@InjectMocks
	MiscService ser;
	
	@Test
	public void getTest() { 
		when(rep.findAll()).thenReturn(Arrays.asList(new Misc(1L, 2, 4, 7), new Misc(1L, 0, 1, 3)));
		List<Misc> miscs = ser.get();
		
		assertTrue(miscs.size()==2);
		
		verify(rep, times(1)).findAll();
		verifyNoMoreInteractions(rep);
	}
	
	@Test
	public void upTest() { 
		when(rep.save(m)).thenReturn(m);
		Misc mm = ser.up(m);
		assertThat(mm, is(equalTo(m)));
	}
	
	@Test
	public void getIdTest() {
		Optional<Misc> m = null;
		when(rep.findById(1L)).thenReturn(m);
		Optional<Misc> e = ser.getById(1L);
		
		assertTrue(e==null);
		
		verify(rep, times(1)).findById(1L);
		verifyNoMoreInteractions(rep);
	}
	
}
