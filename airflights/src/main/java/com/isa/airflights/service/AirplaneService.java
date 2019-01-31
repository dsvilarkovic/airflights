package com.isa.airflights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Airplane;
import com.isa.airflights.repository.AirplaneRepository;

@Service
public class AirplaneService {

	@Autowired
	AirplaneRepository airiplaneRepository;
	
	
	public Airplane findOne(Long id) {
		return airiplaneRepository.getOne(id);
	}

}
