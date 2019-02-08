package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.ReservationPackage;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.repository.ReservationPackageRepository;
import com.isa.airflights.repository.RoomReservationRepository;

@Service
public class RoomReservationService {
	
	@Autowired
	private RoomReservationRepository repository;
	
	@Autowired
	private ReservationPackageRepository packageRepo;
	
	public List<RoomReservation> getAll() {
		
		return repository.findAll();
	}

	public List<RoomReservation> getByRoom(Long room_id) {
		return repository.findByRoom_id(room_id);
	}
	
	public ReservationPackage getRP(Long id) {
		return packageRepo.getOne(id);
	}
	
	public RoomReservation save(RoomReservation r) {
		return repository.save(r);
	}

	public List<RoomReservation> findAll() {
		return repository.findAll();
	}
	
	
	
}
