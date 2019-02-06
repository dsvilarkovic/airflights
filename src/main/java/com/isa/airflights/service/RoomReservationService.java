package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.repository.RoomReservationRepository;

@Service
public class RoomReservationService {
	
	@Autowired
	private RoomReservationRepository repository;
	
	public List<RoomReservation> getAll() {
		
		return repository.findAll();
	}

	public List<RoomReservation> getByRoom(Long room_id) {
		return repository.findByRoom_id(room_id);
	}
}
