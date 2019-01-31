package com.isa.airflights.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.service.AirplaneService;

/**
 * Kontroler za upravljanje kreiranjem aviona i konfiguracijom sedista
 * @author dusan
 *
 */
@RestController
@RequestMapping(value="api/airplane")
@CrossOrigin(origins = "http://localhost:4200")
public class AirplaneController {
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired 
	AirplaneService airplaneService;
	
		
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	
	/**
	 * Metoda za uzimanje aviona iz baze
	 * @return - AirplaneDTO - ako se nadje
	 * 		   - StringJSON - ako se desi greska 
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
					value = "/{id}",
					method = RequestMethod.GET)
	public ResponseEntity<?> getAirplane(@PathVariable Long id){
		//Airplane airplane = airplaneService.findOne(id);
		return null;
	}
	
	/**
	 * Metoda za kreiranje aviona
	 * @return
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE,
					value = "/create",
					method = RequestMethod.POST)
	public ResponseEntity<?> createAirplane(@RequestBody AirplaneDTO airplaneDTO) {
		return null;
	}

}
