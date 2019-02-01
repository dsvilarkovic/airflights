package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.RentACar;
import com.isa.airflights.repository.RentACarRepository;

@Service
public class RentACarService {

	@Autowired
	private RentACarRepository racRepository;
	
	public List<RentACar> findAll() {
		return racRepository.findAll();
	}
	
	public RentACar save(RentACar rac) {
		return racRepository.save(rac);
	}
	
	public RentACar getOne(Long id) {
		return racRepository.getOne(id);
	}
	
	public List<RentACar> findByCity(String city) {
		return racRepository.findByCity(city);
	}
	
}
