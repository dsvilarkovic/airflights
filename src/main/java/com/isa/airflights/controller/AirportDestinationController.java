package com.isa.airflights.controller;

import javax.persistence.EntityNotFoundException;

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

import com.isa.airflights.dto.AirportDestinationDTO;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.service.AirportDestinationService;
import com.isa.airflights.utils.StringJSON;


@RestController
@RequestMapping("api/airport")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportDestinationController {
	@Autowired
	private AirportDestinationService airportDestinationService;
	
	
	
	
	/**
	 * Trazenje destinacije po id-u
	 * @param id - id aerodroma
	 * @return
	 */
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAirportDestination(@PathVariable Long id) {
		AirportDestinationDTO airportDestinationDTO;
		AirportDestination airportDestination;
		try {
			airportDestination = airportDestinationService.getAirportDestination(id);
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<>(new StringJSON("No such airport found"), HttpStatus.NOT_FOUND);
		}
		airportDestinationDTO = airportDestinationService.convertToDTO(airportDestination);
		return new ResponseEntity<AirportDestinationDTO>(airportDestinationDTO, HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value = "/add/{airline_id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addAirportDestination(@RequestBody AirportDestinationDTO airportDestinationDTO, @PathVariable Long airline_id) {
		AirportDestination airportDestination = airportDestinationService.convertToEntity(airportDestinationDTO);
		airportDestinationService.addAirportDestination(airportDestination, airline_id);
		
		return new ResponseEntity<>(new StringJSON("Successfully created new destination!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> deleteAirport(@PathVariable Long id) {
		Boolean success = airportDestinationService.deleteAirportDestination(id);
		
		if(success) {
			return new ResponseEntity<>(new StringJSON("Airport destination successfully deleted!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("No airport found to be deleted"), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> updateAirport(@RequestBody AirportDestinationDTO airportDestinationDTO){
		AirportDestination airportDestination = airportDestinationService.convertToEntity(airportDestinationDTO);
		Boolean success = airportDestinationService.updateAirportDestination(airportDestination);
		
		
		if(success) {
			return new ResponseEntity<>(new StringJSON("Successfully updated airport!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("Error, no such airport found!"), HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
