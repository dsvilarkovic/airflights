package com.isa.airflights.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.VehicleReservationDTO;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleReservation;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.BranchLocationsService;
import com.isa.airflights.service.RentACarService;
import com.isa.airflights.service.VehicleReservationService;
import com.isa.airflights.service.VehicleService;

@RestController
@RequestMapping(value="/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleReservationController {
	@Autowired
	private VehicleService vs;
	
	@Autowired
	private BranchLocationsService bs;
	
	@Autowired
	private RentACarService rs;
	
	@Autowired
	private VehicleReservationService vss;
	
	@Autowired
	private AbstractUserService aus;
	
	
	//vraca listu rezervisanih vozila u datom rent a car servisu (id)
	@RequestMapping(value="/getAll/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleReservationDTO>> getAll(@PathVariable Long id) {
		List<VehicleReservation> lista = vss.findAll();
		List<VehicleReservationDTO> dto = new ArrayList<>();
		for (VehicleReservation v : lista) {
			if(v.getRentacar().getId().equals(id)) {
				dto.add(new VehicleReservationDTO(v));
			}
		}
		
		
		return new ResponseEntity<List<VehicleReservationDTO>>(dto,HttpStatus.OK);
	}
	
	//bukiranje vozila
	@RequestMapping(value="/book/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> book(@PathVariable Long id,@RequestBody VehicleReservationDTO reservation) throws ParseException {
		VehicleReservation vr = new VehicleReservation();
		vr.setVehicle(vs.getOne(reservation.getVehicle().getId()));
		vr.setUser(aus.getOne(reservation.getUser_id()));
		Date pickup = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getPickupdate());
		Date dropoff = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getDropoffdate());
		Date reservationdate = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getReservationdate());
		vr.setDropoffdate(dropoff);
		vr.setPickupdate(pickup);
		vr.setPickUpLocation(reservation.getPickuplocation());
		vr.setDropOffLocation(reservation.getDropofflocation());
		vr.setReservationdate(reservationdate);
		vr.setRentacar(rs.getOne(id));
		vr.setPrice(reservation.getPrice());
		
		vss.save(vr);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
