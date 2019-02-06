package com.isa.airflights.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.Room;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	public List<Room> findByHotel_id(Long hotel_id);
	
}
