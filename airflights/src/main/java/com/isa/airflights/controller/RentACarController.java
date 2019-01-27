package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dom4j.Branch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.RentACarService;

@RestController
@RequestMapping(value="/api/rentacar")
@CrossOrigin(origins = "http://localhost:4200")
public class RentACarController {

	@Autowired
	private RentACarService racService;
	
	@RequestMapping("/test")
	public ResponseEntity<List<RentACar>> getAll() {
		List<RentACar> rac = racService.findAll();
		List<RentACar> r = new ArrayList<RentACar>();
		
		for (RentACar rentACar : rac) {
			r.add(rentACar);
			
		}
				
		return new ResponseEntity<List<RentACar>>(r,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<RentACar> getOne(@PathVariable Long id) {
		return new ResponseEntity<RentACar>(racService.getOne(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{name}")
	public ResponseEntity<List<RentACar>> getAllSearch(@PathVariable String name) {
		List<RentACar> rac = racService.findAll();
		List<RentACar> r = new ArrayList<RentACar>();
		
		for (RentACar rentACar : rac) {
			/*if(rentACar.getCity().contains(name) || rentACar.getName().contains(name)) {
				r.add(rentACar);
			}*/
			r.add(rentACar);
		}
				
		return new ResponseEntity<List<RentACar>>(r,HttpStatus.OK);
	}
	
	
	
}