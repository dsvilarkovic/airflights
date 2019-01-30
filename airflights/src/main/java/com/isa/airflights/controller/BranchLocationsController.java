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

import com.isa.airflights.dto.BranchLocationsDTO;
import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.service.BranchLocationsService;
import com.isa.airflights.service.RentACarService;

@RestController
@RequestMapping(value="/api/branch")
@CrossOrigin(origins = "http://localhost:4200")
public class BranchLocationsController {

	@Autowired
	private BranchLocationsService bls;
	
	@Autowired
	private RentACarService rs;
	
	@RequestMapping("/getAllBranches")
	public ResponseEntity<List<BranchLocationsDTO>> getAllBranches() {
		List<BranchLocations> bl = bls.findAll();
		List<BranchLocationsDTO> branchesDTO = new ArrayList<>();
		for(BranchLocations b : bl) {
			branchesDTO.add(new BranchLocationsDTO(b));
		}
		
		return new ResponseEntity<>(branchesDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addBranch/{id}", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BranchLocationsDTO> addBranch(@RequestBody BranchLocations branch,@PathVariable Long id) {
		System.out.println("Usao u metodu " + branch.getAddress() + branch.getCity());
		/*BranchLocationsDTO v = new BranchLocationsDTO();
		v.setAddress(branch.getAddress());
		v.setCity(branch.getCity());
		
		v.setRentACarId(branch.getRentacar().getId());*/
		RentACar rac = rs.getOne(id);
		/*List<BranchLocations> lista = bls.findAll();
		BranchLocations poslednji = new BranchLocations();
		System.out.println("lista size: " + lista.size());
		poslednji = lista.get(lista.size() - 1);
		System.out.println("Poslednji id: " + poslednji.getId());
		Long pom = poslednji.getId() + 1;
		branch.setId(pom);*/
		branch.setRentacar(rac);
		
		bls.save(branch);
		
		BranchLocationsDTO v = new BranchLocationsDTO();
		v.setAddress(branch.getAddress());
		v.setCity(branch.getCity());
		
		v.setRentACarId(branch.getRentacar().getId());
		
		return new ResponseEntity<BranchLocationsDTO>(v,HttpStatus.CREATED);
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
	
	//proverava da li postoji vozilo u trazenoj (pick up) filijali
	@RequestMapping(value="/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<BranchLocationsDTO>searchBranch(@PathVariable Long id) {
		List<BranchLocations> lista = bls.findAll();
		BranchLocationsDTO bdto;
		for (BranchLocations b : lista) {
			if(b.getId().equals(id)) {
				bdto = new BranchLocationsDTO(b);
				return new ResponseEntity<BranchLocationsDTO>(bdto,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
