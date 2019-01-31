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

import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.service.ExtrasService;

@RestController
@RequestMapping(value="/api/extras")
@CrossOrigin(origins = "http://localhost:4200")
public class ExtrasController {
	
	@Autowired
	ExtrasService service;
	
	/**
	 * Uzima dodatne pogodnosti koje postoje u hotelu
	 */
    @RequestMapping(value="/hotel/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelExtras>> getExtrasInHotel(@PathVariable("id") Long id) {
    	
    	List<HotelExtras> extras = service.getExtrasByHotel(id);
    	
    	return new ResponseEntity<List<HotelExtras>>(extras, HttpStatus.OK);
    }
    
	@RequestMapping(value="/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<HotelExtras> getHotelExtra(@PathVariable("id") Long id) {
		
		HotelExtras e = service.getOne(id);
    	
    	return new ResponseEntity<HotelExtras>(e, HttpStatus.OK);
    }
    
	@RequestMapping(value="/add", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelExtras> addExtra(@RequestBody HotelExtras extra) {
		
		HotelExtras e = service.save(extra);
    	
    	return new ResponseEntity<HotelExtras>(e, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelExtras> deleteExtra(@PathVariable("id") Long id) {
		
		service.delete(id);
    	
    	return new ResponseEntity<HotelExtras>(HttpStatus.CREATED); 
    }
	
	@RequestMapping(value="/", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<HotelExtras> updateHotelExtra(@RequestBody HotelExtras extra) {
		
		HotelExtras e = service.update(extra);
    	
    	return new ResponseEntity<HotelExtras>(e, HttpStatus.OK);
    }
	
}
