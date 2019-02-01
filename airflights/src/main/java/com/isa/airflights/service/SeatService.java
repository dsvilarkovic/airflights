package com.isa.airflights.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.airflights.model.Seat;
import com.isa.airflights.repository.SeatRepository;

public class SeatService {
	
	@Autowired
	SeatRepository seatRepository;
	
	public void saveSeat(Seat seat) {
		seatRepository.save(seat);
	}

}
