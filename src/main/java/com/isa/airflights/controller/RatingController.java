package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.RentACarDTO;
import com.isa.airflights.dto.RoomResMockDTO;
import com.isa.airflights.dto.VehicleReservationDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.Room;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleReservation;
import com.isa.airflights.repository.HotelRepository;
import com.isa.airflights.repository.RoomRepository;
import com.isa.airflights.repository.RoomReservationRepository;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.RentACarService;
import com.isa.airflights.service.VehicleReservationService;
import com.isa.airflights.service.VehicleService;



@RestController
@RequestMapping(value="/api/rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {
	
	@Autowired
	private RentACarService racService;
	
	@Autowired
	private VehicleService vs;
	
	@Autowired
	private VehicleReservationService vss;
	
	@Autowired
	private RoomReservationRepository rrr;
	
	@Autowired
	private RoomRepository roomrep;
	
	@Autowired
	private HotelRepository hr;
	
	
	/**
	 * Metoda koja ocenjuje rent a car servis
	 * tri parametra, prvi je ocena, drugi id rent acar servisa koji je ocenjen, a treci id rezervacije
	 * */
	@RequestMapping("/rate/{rate}/{id}/{user}")
	public ResponseEntity<VehicleReservationDTO> setRateRentACar(@PathVariable String rate,@PathVariable Long id,@PathVariable Long user) {
		RentACar rac = racService.getOne(id);
		VehicleReservation vr = vss.getOne(user);
		

		vr.setRateRentacar(true); // setovali smo na rezervaciji da je ocenjen rent a car servis
		
		VehicleReservationDTO dto2 = new VehicleReservationDTO(vr);
		
		System.out.println("Usao ?");
		
		int r = Integer.parseInt(rate); //trenutno ocenjen - ocena korisnika

		//rac.setRating(rating); //stavimo u tabelu rent a car-a
		double pomdfs = rac.getRatingsCount();
		System.out.println("Count " + pomdfs);
		pomdfs++;
		rac.setRatingsCount(pomdfs);
		
		double pom2 = rac.getRatingsSum();
		System.out.println("Sum " + pom2);
		pom2 += r;
		rac.setRatingsSum(pom2);

		racService.save(rac);


				
		return new ResponseEntity<VehicleReservationDTO>(dto2,HttpStatus.OK);
	}
	
	
	/**
	 * Metoda koja ocenjuje vozilo servis
	 * tri parametra, prvi je ocena, drugi id rent acar servisa koji je ocenjen, a treci id rezervacije
	 * user- id rezervacije
	 * */
	@RequestMapping("/rate/vehicle/{rate}/{id}/{user}")
	public ResponseEntity<VehicleReservationDTO> setRateVehicle(@PathVariable String rate,@PathVariable Long id,@PathVariable Long user) {
		Vehicle veh = vs.getOne(id);
		VehicleReservation vr = vss.getOne(user);
		

		vr.setRateVehicle(true); // setovali smo na rezervaciji da je ocenjen rent a car servis
		
		
		
		System.out.println("Usao ? " + rate);
		
		int r = Integer.parseInt(rate); //trenutno ocenjen - ocena korisnika

		//rac.setRating(rating); //stavimo u tabelu rent a car-a
		double pomdfs = veh.getRatingsCount();
		System.out.println("Count " + pomdfs);
		pomdfs++;
		veh.setRatingsCount(pomdfs);
		
		double pom2 = veh.getRatingsSum();
		System.out.println("Sum " + pom2);
		pom2 += r;
		veh.setRatingsSum(pom2);
		
		double res = pom2/pomdfs;
		
		veh.setRating(res);

		vs.save(veh);

		VehicleReservationDTO dto2 = new VehicleReservationDTO(vr);
				
		return new ResponseEntity<VehicleReservationDTO>(dto2,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja ocenjuje sobus
	 * tri parametra, prvi je ocena, drugi id rent acar servisa koji je ocenjen, a treci id rezervacije
	 * user- id rezervacije
	 * */
	@RequestMapping("/rate/room/{rate}/{id}/{user}")
	public ResponseEntity<RoomResMockDTO> setRateRoom(@PathVariable String rate,@PathVariable Long id,@PathVariable Long user) {
		Room rac = roomrep.getOne(id);
		RoomReservation rr = rrr.getOne(user); //id rezervacije
		

		rr.setRateRoom(true);// setovali smo na rezervaciji da je ocenjen rent a car servis
		
		RoomResMockDTO dto2 = new RoomResMockDTO(rr);

		
		System.out.println("Usao ?");
		
		int r = Integer.parseInt(rate); //trenutno ocenjen - ocena korisnika

		//rac.setRating(rating); //stavimo u tabelu rent a car-a
		Long pomdfs = rac.getRatingsCount();
		System.out.println("Count " + pomdfs);
		pomdfs++;
		rac.setRatingsCount(pomdfs);
		
		Long pom2 = rac.getRatingsSum();
		System.out.println("Sum " + pom2);
		pom2 += r;
		rac.setRatingsSum(pom2);

		roomrep.save(rac);


				
		return new ResponseEntity<RoomResMockDTO>(dto2,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja ocenjuje hotel
	 * tri parametra, prvi je ocena, drugi id rent acar servisa koji je ocenjen, a treci id rezervacije
	 * user- id rezervacije
	 * */
	@RequestMapping("/rate/hotel/{rate}/{id}/{user}")
	public ResponseEntity<RoomResMockDTO> setRateHotel(@PathVariable String rate,@PathVariable Long id,@PathVariable Long user) {
		Hotel rac = hr.getOne(id);
		RoomReservation rr = rrr.getOne(user); //id rezervacije
		

		rr.setRateHotel(true);// setovali smo na rezervaciji da je ocenjen rent a car servis
		
		RoomResMockDTO dto2 = new RoomResMockDTO(rr);

		
		System.out.println("Usao ?");
		
		int r = Integer.parseInt(rate); //trenutno ocenjen - ocena korisnika

		//rac.setRating(rating); //stavimo u tabelu rent a car-a
		Long pomdfs = rac.getRatingsCount();
		System.out.println("Count " + pomdfs);
		pomdfs++;
		rac.setRatingsCount(pomdfs);
		
		Long pom2 = rac.getRatingsSum();
		System.out.println("Sum " + pom2);
		pom2 += r;
		rac.setRatingsSum(pom2);

		hr.save(rac);


				
		return new ResponseEntity<RoomResMockDTO>(dto2,HttpStatus.OK);
	}
	
	
	
	

}
