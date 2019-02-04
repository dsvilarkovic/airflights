package com.isa.airflights.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.RoomReservation;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	public List<RoomReservation> findByRoom_id(Long room_id);
}

