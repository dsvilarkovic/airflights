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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.repository.AbstractUserRepository;
import com.isa.airflights.service.AdminService;
import com.isa.airflights.testing.constants.HotelConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
	
	@Mock
	private AbstractUserRepository repository;
	
	@Mock
	private AbstractUser user;
	
	@InjectMocks
	private AdminService ser;
	
	@Test
	public void testGetAll() {
		when(repository.findAll()).thenReturn(Arrays.asList(new AbstractUser()));
		Collection<AbstractUser> abs = ser.getAll();
		
		assertTrue(abs.size()==1);
		
		verify(repository, times(1)).findAll();
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void testSave() {
		when(repository.findAll()).thenReturn(Arrays.asList(new AbstractUser(1L, "Pera", "Peric", "pera@gmail.com", "aa", "aa", "123", true, 1, true)));
		AbstractUser u = new AbstractUser(2L, "Mika", "Mikic", "mika@gmail.com", "aa", "aa", "123", true, 1, true);
		
		when(repository.save(u)).thenReturn(u);
		
		int size1 = ser.getAll().size();
		
		AbstractUser sa = ser.save(u);
		
		assertThat(sa).isNotNull();
		
		when(repository.findAll()).thenReturn(Arrays.asList(new AbstractUser(1L, "Pera", "Peric", "pera@gmail.com", "aa", "aa", "123", true, 1, true), sa));
		
		List<AbstractUser> list = (List<AbstractUser>)ser.getAll();
		
		sa = list.get(size1 - 1);
		
		assertThat(sa.getFirstName()).isEqualTo("Pera");
		assertThat(sa.getLastName()).isEqualTo("Peric");
		
		verify(repository, times(2)).findAll();
		verify(repository).save(u);
		verifyNoMoreInteractions(repository);
		
	}
	
	@Test
	public void testSave2() {
		when(repository.save(user)).thenReturn(user);
		AbstractUser a = ser.save(user);
		assertThat(a, is(equalTo(user)));
	}
	
	@Test
	public void testGetOne() {
		when(repository.getOne(1L)).thenReturn(user);
		AbstractUser l = ser.findOne(1L);
		assertEquals(l, user);
		
		verify(repository, times(1)).getOne(HotelConstants.DB_ID);
		verifyNoMoreInteractions(repository);
	}

}
