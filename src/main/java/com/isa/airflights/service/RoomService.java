package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Room;
import com.isa.airflights.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;
	
	public Room getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Room> getAll() {
		return repository.findAll();
	}
	
	public List<Room> getRoomByHotel(Long id) {
		return repository.findByHotel_id(id);
	}

	public Room save(Room room) {
		return repository.save(room);
	}	

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Room update(Room room) {
		return repository.save(room);
	}

	public Room endPromo(Room room) {
		room.setDiscount(null);
		room.setPromo(false);
		
		return repository.save(room);
	}
	
}
