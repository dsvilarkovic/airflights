package com.isa.airflights.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.model.RentACar;
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
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public VehicleReservation save(VehicleReservation rac) {
		return vr.save(rac);
	}
	
	public List<VehicleReservation> findAllReserved(RentACar r, Date pickupdate, Date dropoffdate) {
		return vr.findAllReserved(r, pickupdate, dropoffdate);
	}
	
	public List<VehicleReservation> getAllByDate(RentACar r, Date pickupdate, Date dropoffdate) {
		return vr.getAllByDate(r,pickupdate,dropoffdate);
	}
	
	
	public void delete(Long id) {
			System.out.println("U delete: " + id);
			vr.deleteById(id);

	}
	
}
