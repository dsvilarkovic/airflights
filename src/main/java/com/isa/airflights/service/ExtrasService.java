package com.isa.airflights.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.model.RoomResExtras;
import com.isa.airflights.repository.ExtrasRepository;
import com.isa.airflights.repository.RoomResExtrasRepo;

@Service
public class ExtrasService {
	
	@Autowired
	private ExtrasRepository repository;
	
	@Autowired
	private RoomResExtrasRepo rrx;
	
	public Collection<HotelExtras> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	public HotelExtras getOne(Long id) {
		return repository.getOne(id);
	}

	@Transactional
	public HotelExtras save(HotelExtras extra) {
		return repository.save(extra);
	}

	public HotelExtras update(HotelExtras extra) {
		return repository.save(extra);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<HotelExtras> getExtrasByHotel(Long id) {
		return repository.findByHotel_id(id);
	}
	
	public RoomResExtras saveResExtra(RoomResExtras r) {
		return rrx.save(r);
	}
	
	
	
}
