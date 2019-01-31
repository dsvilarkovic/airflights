package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.repository.AbstractUserRepository;


@Service
public class AbstractUserService {

	
	@Autowired
	private AbstractUserRepository abstractUserRepository;
	


	public List<AbstractUser> findAll() {
		return abstractUserRepository.findAll();
	}
	
	public Page<AbstractUser> findAll(Pageable page) {
		return abstractUserRepository.findAll(page);
	}

	public AbstractUser save(AbstractUser AbstractUser) {
		return abstractUserRepository.save(AbstractUser);
	}


	
	public AbstractUser findByIndex(String index) {
		return abstractUserRepository.findOneByIndex(index);
	}
	
	
}
