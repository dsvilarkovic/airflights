package com.isa.airflights.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Hotel;
import com.isa.airflights.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository repository;

	public Collection<Hotel> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	public Hotel getOne(Long id) {
		return repository.getOne(id);
	}

	public void save(Hotel hotel) {
		repository.save(hotel);
	}

	public void update(Hotel hotel) {
		repository.save(hotel);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void getRooms(Long id) {
		
		
	}
	
	
	
}
