package com.isa.airflights.controller;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

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

import com.isa.airflights.model.Hotel;
import com.isa.airflights.service.HotelService;

@RestController
@RequestMapping(value="/api/hotel")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
	
	@Autowired
	HotelService service;

    @GetMapping("/list")
    public Collection<Hotel> hotels() {
    	//System.out.println("Pokupio");
        return service.getAll();
    }
    
    @GetMapping("/{id}" )
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id) {
    	
    	Hotel hotel;
    	try {
    		 hotel = service.getOne(id);
    	} catch (EntityNotFoundException e) {
    		return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
		}
    	
        return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
    }
    
    @RequestMapping(value="/add", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		
    	service.save(hotel);
    	
    	return new ResponseEntity<Hotel>(hotel,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/", method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
		
    	service.update(hotel);
    	
    	return new ResponseEntity<Hotel>(hotel,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> deleteHotel(@PathVariable("id") Long id) {
		
    	service.delete(id);
    	
    	return new ResponseEntity<Hotel>(HttpStatus.OK);
    }
    
    /**
     * Ne koristi se za sada
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}/rooms", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> getRooms(@PathVariable("id") Long id) {
		
    	Hotel h = service.getOne(id);
    	System.out.println(h.getName());
    	System.out.println(h.getAddress());
    	service.getRooms(id);
    	
    	return new ResponseEntity<Hotel>(HttpStatus.OK);
    }
}
