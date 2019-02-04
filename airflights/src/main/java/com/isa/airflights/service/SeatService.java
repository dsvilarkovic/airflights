package com.isa.airflights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Seat;
import com.isa.airflights.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	public void saveSeat(Seat seat) {
		seatRepository.save(seat);
	}

}
