package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.RentACarDTO;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.service.RentACarService;

@RestController
@RequestMapping(value="/api/rentacar")
@CrossOrigin(origins = "http://localhost:4200")
public class RentACarController {

	@Autowired
	private RentACarService racService;
	
	@RequestMapping("/test")
	public ResponseEntity<List<RentACarDTO>> getAll() {
		List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			r.add(new RentACarDTO(rentACar));
			
		}
				
		return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<RentACarDTO> getOne(@PathVariable Long id) {
		System.out.println("Provera? " + id);
		RentACar r = racService.getOne(id);
		System.out.println("Ovde? " + r.getName());
		RentACarDTO dto = new RentACarDTO(r);
		System.out.println("Provera? " + dto.getName());
		return new ResponseEntity<RentACarDTO>(dto,HttpStatus.OK);
	}
	
	
	@RequestMapping("/search/{name}")
	public ResponseEntity<List<RentACarDTO>> getSearch(@PathVariable String name) {
		List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			if(rentACar.getName().contains(name) || rentACar.getCity().contains(name))
				r.add(new RentACarDTO(rentACar));
			
		}
				
		return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
	}
		
    @RequestMapping(value="/", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentACar> addAgency(@RequestBody RentACar rac) {
		
    	RentACar added = racService.save(rac);
    	
    	return new ResponseEntity<RentACar>(added,HttpStatus.OK);
    }
    
    @GetMapping("/allRacs")
    public ResponseEntity<List<RentACarDTO>> getAllRacs() {
    	List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			r.add(new RentACarDTO(rentACar));
		}
		
        return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
    }
    
	
	
}
