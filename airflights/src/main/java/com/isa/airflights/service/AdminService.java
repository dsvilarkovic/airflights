package com.isa.airflights.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.repository.AbstractUserRepository;

@Service
public class AdminService {
	
	@Autowired
	private AbstractUserRepository repository;
	
	public Collection<AbstractUser> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	public AbstractUser save(AbstractUser abstractUser) {
		return repository.save(abstractUser);
	}

	public void remove(Long id) {
		repository.deleteById(id);;
	}

}
