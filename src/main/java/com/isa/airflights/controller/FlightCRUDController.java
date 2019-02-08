package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.dto.FlightDTO;
import com.isa.airflights.dto.SeatDTO;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightTicket;
import com.isa.airflights.model.Seat;
import com.isa.airflights.service.AirplaneService;
import com.isa.airflights.service.FlightService;
import com.isa.airflights.service.FlightTicketService;
import com.isa.airflights.utils.StringJSON;

/**
 * Kontroler koji ce se koristiti za CRUD operacije nad letovima
 * @author dusan
 *
 */
@RestController
@RequestMapping("api/flight")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightCRUDController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private AirplaneService airplaneService;
	
	@Autowired
	private FlightTicketService flightTicketService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * Trazenje leta po id-u
	 * @param id - id leta
	 * @return
	 */
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFlight(@PathVariable Long id) {		
		FlightDTO flightDTO;
		Flight flight;
		try {
			//airportDestination = airportDestinationService.getAirportDestination(id);
			
			flight = flightService.getFlight(id);
			
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<>(new StringJSON("No such flight found"), HttpStatus.NOT_FOUND);
		}
		flightDTO = flightService.convertToDTO(flight);
		return new ResponseEntity<FlightDTO>(flightDTO, HttpStatus.OK);
		
	}
	
	
	
	/**
	 * Dodavanje leta
	 * @param flightDTO
	 * @return
	 */
	@RequestMapping(value = "/add",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addFlight(@RequestBody FlightDTO flightDTO) {
		Flight flight = flightService.convertToEntity(flightDTO);
		flightService.addFlight(flight);
		
		return new ResponseEntity<>(new StringJSON("Successfully created new flight!"), HttpStatus.OK);
	}
	
	/**
	 * Brisanje konkretnog leta
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}",
					method = RequestMethod.DELETE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteFlight(@PathVariable Long id){
		Boolean success = flightService.deleteFlight(id);
		
		if(success) {
			return new ResponseEntity<>(new StringJSON("FLight successfully deleted!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("Flight not found!"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Azuriranje konkretnog leta
	 * @param flightDTO
	 * @return
	 */
	@RequestMapping(value = "/update",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateFlight(@RequestBody FlightDTO flightDTO){
		Flight flight = flightService.convertToEntity(flightDTO);
		
		Boolean success = flightService.updateFlight(flight);
		
		if(success) {
			return new ResponseEntity<>(new StringJSON("Flight successfully updated!"), HttpStatus.OK);		
			
		}
		return new ResponseEntity<>(new StringJSON("Error, no such flight found"), HttpStatus.NOT_FOUND);
		
	}
	

	/**
	 * Ispis svih letova koji postoje	
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value = "/find/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFlights(Pageable pageRequest){
		Page<Flight> flights = flightService.findAll(pageRequest);
		
		List<FlightDTO> flightDTOs = new ArrayList<>();
		for (Flight flight : flights) {
			FlightDTO flightDTO = flightService.convertToDTO(flight);
			flightDTOs.add(flightDTO);
		}
		
		Page<FlightDTO> ret = new PageImpl<>(flightDTOs, pageRequest, flights.getTotalElements());
		return ResponseEntity.ok(ret);
		//return new ResponseEntity<>(flightDTOs, HttpStatus.OK);
	}
	
	
	/**
	 * Vraca avion koji se koristi za odabrani let
	 */
	@RequestMapping(value = "/airplane/{flight_id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAirplaneByFlight(@PathVariable Long flight_id){
		
		Airplane airplane;
		try {
			airplane = airplaneService.findByFlight_Id(flight_id);
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<StringJSON>(new StringJSON("Airplane not found"), HttpStatus.NOT_FOUND);
		}
		
		AirplaneDTO airplaneDTO = airplaneService.convertToDTO(airplane);
		return new ResponseEntity<AirplaneDTO>(airplaneDTO, HttpStatus.OK);
		
	}
	
	
	/**
	 * Uzmi sva vec rezervisana sedista
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping(value = "/reserved-seats/{flight_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getReservedSeatsForFlight(@PathVariable Long flight_id){
		List<FlightTicket> flightTickets = flightTicketService.findAllByFlight_Id(flight_id);
		
		//napravi seatDTO set, i posalji ih nazad
		List<SeatDTO> seatDTOs = new ArrayList<>();
		
		for (FlightTicket flightTicket : flightTickets) {
			Seat seat = flightTicket.getSeat();
			SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);
			seatDTOs.add(seatDTO);
		}
		
		
		return ResponseEntity.ok(seatDTOs);
	}
	
}