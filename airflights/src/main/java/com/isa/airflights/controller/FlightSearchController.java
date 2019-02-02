package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.FlightDTO;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightClassPrice;
import com.isa.airflights.model.enumtypes.AirlineClassType;
import com.isa.airflights.model.enumtypes.FlightType;
import com.isa.airflights.repository.FlightRepository;
import com.isa.airflights.service.FlightService;
import com.sun.mail.handlers.image_gif;

/**
 * Kontroler koji sluzi za pretragu po letovima
 * @author dusan
 */
@RestController
@RequestMapping("api/flight/search/")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightSearchController {
	
	
	@Autowired
	FlightService flightService;
	
	@Autowired 
	FlightCRUDController flightCRUDController;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@RequestMapping(value = "/",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchByExample(
				@RequestParam(value = "arrival_id", required = true) Long arrival_id,
				@RequestParam(value = "departure_id", required = true) Long departure_id,
				@RequestParam(value = "departure_date_time", required = true) Date departureDatetime,
				@RequestParam(value = "arrival_date_time", required = true) Date arrivalDatetime,
				@RequestParam(value = "flight_type", required = false) FlightType flightType,
				@RequestParam(value = "persons_num", required = false) Integer persons_num,
				@RequestParam(value = "class_type", required = false) AirlineClassType airlineClassType,
				@RequestParam(value = "luggage_count", required = false) Integer luggageCount			
			){
		
		
				
		List<Flight> flights = flightService.findAllByArrivalIdAndDepartureIdAndDepartureDatetimeAndArrivalDatetime
				(arrival_id, departure_id, departureDatetime, arrivalDatetime);
		
		
		if(flightType != null)
			flights.removeIf(flight -> flight.getFlightType().equals(flightType) == false);
		
		if(airlineClassType != null)
			flights.removeIf(flight -> ifFlightNotContainsClass(flight, airlineClassType));
		
		//TODO: dodati proveru da li je trazeni broj putnika manji od broja raspolozivih mesta na letu
		
		
		//TODO: dodati proveru da li je trazeni broj prtljaga manji od broja raspolozivih mesta za prtljag
		
		
		
		
		List<FlightDTO> flightDTOs = new ArrayList<FlightDTO>();
		
		return new ResponseEntity<>(flightDTOs, HttpStatus.OK);
	}
	
	
	public Boolean ifFlightNotContainsClass(Flight flight, AirlineClassType airlineClassType) {
		Set<FlightClassPrice> flightClassPrices = flight.getFlightClassPrices();
		for (FlightClassPrice flightClassPrice : flightClassPrices) {
			if(flightClassPrice.getAirlineClassType().equals(airlineClassType)) {
				return false;
			}
		}
		return true;
	}
}
