package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Misc;
import com.isa.airflights.repository.MiscRepository;

@Service
public class MiscService {
	
	@Autowired
	private MiscRepository repo;
	
	public List<Misc> get() {
		return repo.findAll();
	}
	
	public Misc up(Misc m) {
		return repo.save(m);
	}
	
}
