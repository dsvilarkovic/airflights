package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.model.Vehicle;
import com.isa.airflights.service.VehicleService;

@RestController
@RequestMapping(value="/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

	@Autowired
	private VehicleService vs;
	
	@RequestMapping("/test")
	public ResponseEntity<List<Vehicle>> getAll() {
		System.out.println("sadfsdaf");
		List<Vehicle> v = vs.findAll();
		
		List<Vehicle> r = new ArrayList<Vehicle>();
		
		for (Vehicle rentACar : v) {
			r.add(rentACar);
			
		}
		
		System.out.println("Pred return");
		return new ResponseEntity<List<Vehicle>>(r,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addVehicle", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
		System.out.println("Usao u metodu");
		Vehicle v = new Vehicle();
		v.setName(vehicle.getName());
		v.setBrand(vehicle.getBrand());
		v.setModel(vehicle.getModel());
		v.setYear(vehicle.getYear());
		v.setSeats(vehicle.getSeats());
		v.setType(vehicle.getType());
		v.setPrice(vehicle.getPrice());
		//v.setRentacar(vehicle.getRentacar());
		v.setBranch_locations(vehicle.getBranch_locations());
		v.setRating(0);
		v.setReserved(false);
		
		System.out.println("GetBranch: " + v.getBranch_locations());
		
		v = vs.save(v);
		
		return new ResponseEntity<Vehicle>(new Vehicle(v),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle){
		Vehicle v = vs.getOne(vehicle.getId());
		if(v != null){
			Vehicle u = vs.update(v, vehicle);
			if(u == null) {
				return null;
			} else {
				return new ResponseEntity<Vehicle>(new Vehicle(u), HttpStatus.OK );
			}
			
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?>  deleteVehicle(@PathVariable Long id) {
		Vehicle v = vs.delete(id);
		if(v == null) {
			//nismo obrisali
			System.out.println("Objekat je nepostojeci li je reservisan");
			return new ResponseEntity<Boolean>(false,HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
