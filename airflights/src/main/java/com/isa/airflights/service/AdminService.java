package com.isa.airflights.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.AbstractUser;

@Service
public class AdminService {
	
	@Autowired
	private AbstractUserService repository;
	
	public Collection<AbstractUser> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

}
