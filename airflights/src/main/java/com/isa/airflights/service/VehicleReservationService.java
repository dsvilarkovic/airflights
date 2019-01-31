package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleReservation;
import com.isa.airflights.repository.VehicleReservationRepository;

@Service
public class VehicleReservationService {

	@Autowired
	private VehicleReservationRepository vr;
	
	public List<VehicleReservation> findAll() {
		return vr.findAll();
	}

	public VehicleReservation getOne(Long id) {
		return vr.getOne(id);
	}
	
	public VehicleReservation save(VehicleReservation rac) {
		return vr.save(rac);
	}
}
