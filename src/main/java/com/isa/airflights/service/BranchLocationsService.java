package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.repository.BranchLocationsRepository;


@Service
public class BranchLocationsService {

	@Autowired
	private BranchLocationsRepository blRepository;
	
	public List<BranchLocations> findAll() {
		return blRepository.findAll();
	}
	
	public BranchLocations save(BranchLocations rac) {
		return blRepository.save(rac);
	}
	
	public BranchLocations getOne(Long id) {
		return blRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public BranchLocations update(BranchLocations old_vehicle, BranchLocations new_vehicle) {
		// TODO Auto-generated method stub
		if( new_vehicle.getAddress() != null){
			old_vehicle.setAddress(new_vehicle.getAddress());
		}
		if(new_vehicle.getCity() != null) {
			old_vehicle.setCity(new_vehicle.getCity());
		}
		
		
		

		return blRepository.save(old_vehicle);
	}
	
	public void delete(Long id) {
		System.out.println("Usao u delete na servisu?");
		BranchLocations v = blRepository.getOne(id);
		if(v != null) {
			System.out.println("U delete: " + v.getId());
			blRepository.deleteById(v.getId());
		}
	}
	
}
