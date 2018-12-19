package com.isa.airflights.controller;

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

import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.service.BranchLocationsService;

@RestController
@RequestMapping(value="/api/branch")
@CrossOrigin(origins = "http://localhost:4200")
public class BranchLocationsController {

	@Autowired
	private BranchLocationsService bls;
	
	@RequestMapping("/getAllBranches")
	public ResponseEntity<List<BranchLocations>> getAllBranches() {
		List<BranchLocations> bl = bls.findAll();
		return new ResponseEntity<List<BranchLocations>>(bl,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addBranch", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BranchLocations> addBranch(@RequestBody BranchLocations branch) {
		System.out.println("Usao u metodu");
		BranchLocations v = new BranchLocations();
		v.setAddress(branch.getAddress());
		v.setCity(branch.getCity());
		
		v.setRentacar(branch.getRentacar());
		
		v = bls.save(v);
		
		return new ResponseEntity<BranchLocations>(new BranchLocations(v),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<BranchLocations> updateBranchLocations(@RequestBody BranchLocations bl){
		BranchLocations v = bls.getOne(bl.getId());
		if(v != null){
			BranchLocations u = bls.update(v, bl);
			return new ResponseEntity<BranchLocations>(new BranchLocations(u), HttpStatus.OK );
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?>  deleteBranch(@PathVariable Long id) {
		bls.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
