package com.isa.airflights.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.VehicleDTO;
import com.isa.airflights.dto.VehicleReservationDTO;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleReservation;
import com.isa.airflights.service.AbstractUserService;
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
	
	/**
	 * Metoda koja bi trebalo da vrati sva vozila koja su slobodna za dat vremenski period
	 * @param date1 od
	 * @param date2 do
	 * */
	@RequestMapping(value="/checkDate/{date1}/{date2}/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleDTO>> checkDate(@PathVariable String date1,@PathVariable String date2,@PathVariable Long id) throws ParseException {
		System.out.println("Jel sam usao ?");
		RentACar r = rs.getOne(id);
		
		
		Date pickup = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
		Date dropoff = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		List<VehicleReservation> listaRez = vss.findAllReserved(r, pickup, dropoff);
		List<Vehicle> listaAll = vs.findAll();
		List<VehicleDTO> dto = new ArrayList<>();
		
		if(listaRez.isEmpty()) {
			
		}else {
			for (VehicleReservation l : listaRez) {
				System.out.println("Sta je ovo: "+ l.getVehicle().getId()+ " " + l.getPickupdate().toString() + " " + l.getDropoffdate().toString());
				listaAll.remove(l.getVehicle());
			}
		}
		
		
		for (Vehicle v : listaAll) {
			dto.add(new VehicleDTO(v));
		}
		
	/*	Vehicle vozilo = vs.getOne(id);
		
		
		for (VehicleReservation v : lista) {
			if(v.getRentacar().getId().equals(id)) {
				if(pickup.getDate() < v.getDropoffdate().getDate() && pickup.getDate() > v.getPickupdate().getDate()) {
					System.out.println("Prvi datum je ok, ne nalazi se izmedju ovog vozila");
				}
				if(pickup.getDate() < v.getDropoffdate().getDate() && pickup.getDate() > v.getPickupdate().getDate()) {
					System.out.println("Prvi datum je ok, ne nalazi se izmedju ovog vozila");
				}
			}
		}*/
		
		return new ResponseEntity<List<VehicleDTO>>(dto,HttpStatus.OK);
		
	}
	
}
