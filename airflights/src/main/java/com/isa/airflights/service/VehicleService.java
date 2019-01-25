package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Vehicle;
import com.isa.airflights.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vr;
	
	public List<Vehicle> findAll() {
		return vr.findAll();
	}
	
	public Vehicle save(Vehicle rac) {
		return vr.save(rac);
	}
	
	public Vehicle getOne(Long id) {
		return vr.getOne(id);
	}
	
	
	public Vehicle update(Vehicle old_vehicle, Vehicle new_vehicle) {
		// TODO Auto-generated method stub
		
		if(old_vehicle.getReserved()) {
			return null;
		} else {
			if( new_vehicle.getName() != null){
				old_vehicle.setName(new_vehicle.getName());
			}
			if(new_vehicle.getModel() != null) {
				old_vehicle.setModel(new_vehicle.getModel());
			}
			if(new_vehicle.getBrand() != null) {
				old_vehicle.setBrand(new_vehicle.getBrand());
			}
			if((Double)new_vehicle.getPrice() != null) {
				old_vehicle.setPrice(new_vehicle.getPrice());
			}
			if((Integer)new_vehicle.getSeats() != null) {
				old_vehicle.setSeats(new_vehicle.getSeats());
			}
			if((Integer)new_vehicle.getYear() != null) {
				old_vehicle.setYear(new_vehicle.getYear());
			}
			if(new_vehicle.getBranch_locations() != null) {
				old_vehicle.setBranch_locations(new_vehicle.getBranch_locations());
			}
			
			

			return vr.save(old_vehicle);
		}
		
		
	}
	
	public Vehicle delete(Long id) {
		System.out.println("Usao u delete na servisu?");
		Vehicle v = vr.getOne(id);
		if(v != null) {
			//ako je reservisano ne moze se obrisati
			if(v.getReserved()) {
				return null;
			} else {
				System.out.println("U delete: " + v.getId());
				vr.deleteById(v.getId());
				return v;
			}
		}
		return v;
	}
	
}
