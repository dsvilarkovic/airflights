package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.dto.FlightTicketDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightTicket;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.AirlineService;
import com.isa.airflights.service.FlightService;
import com.isa.airflights.service.FlightTicketService;
import com.isa.airflights.utils.StringJSON;


/**
 * Kontroler za CRUD operacije nad kartama
 * @author dusan
 *
 */
@RestController
@RequestMapping("api/flight-ticket")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightTicketController {
	
	@Autowired
	private FlightTicketService flightTicketService;
	@Autowired 
	private AbstractUserService abstractUserService;
	
	@Autowired 
	private AirlineService airlineService;
	
	@Autowired 
	private FlightService flightService;
	
	/**
	 * Kreiranje karte, ne nuzno vezano za korisnika 
	 * @param flightTicketDTO
	 * @return
	 */
	@RequestMapping(value = "/create/fast/",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> createFastFlightTicket(@RequestBody FlightTicketDTO flightTicketDTO){
		
		 //Svaka karta iz liste
		 //ima podatak o polaznoj i odredišnoj destinaciji, datumu, 
		 //vremenu, mestu u avionu, originalnoj ceni i
		 //popustu
		FlightTicket flightTicket = flightTicketService.convertToEntity(flightTicketDTO);
		
		
		
		flightTicketService.saveFlightTicket(flightTicket);
		
		return new ResponseEntity<>(new StringJSON("Successfully created fast flight ticket"), HttpStatus.OK);		
	}
		
	/**
	 * Metoda za citanje podataka o karti
	 */
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> getFlightTicket(@PathVariable Long id){
		 FlightTicket flightTicket = null;
		 try {
			 flightTicket = flightTicketService.getFlightTicket(id);
		 }
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
		 }
		 
		 FlightTicketDTO flightTicketDTO = flightTicketService.convertToDTO(flightTicket);
		return new ResponseEntity<>(flightTicketDTO, HttpStatus.OK);
		//return new ResponseEntity<>(new StringJSON("Successfully reserved fast flight ticket"), HttpStatus.OK);		
	}
	
	
	/**
	 * Metoda za brisanje karte
	 */
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> deleteFlightTicket(@PathVariable Long id){		
		 try {
			 flightTicketService.getFlightTicket(id);
		 }
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
		 }
		 Boolean success = flightTicketService.deleteFlightTicket(id);
		 
		 if(success) {
			 return new ResponseEntity<>(new StringJSON("Successfully deleted flight ticket"),  HttpStatus.OK);
		 }
		 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Metoda za rezervisanje karte za korisnika,
	 * Ako nije za nas, onda se salje mejl,
	 * napomena, mora se prethodno kreirati karta
	 */
	@RequestMapping(value = "/reserve/{flight_id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> reserveFlightTicket(@PathVariable Long flight_id,
												 @RequestBody AbstractUserDTO abstractUserDTO){
		 FlightTicket flightTicket = null;
		 try {
			 flightTicket = flightTicketService.getFlightTicket(flight_id);
		 }
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
		 }
		 //konvertuj ga
		 AbstractUser user = abstractUserService.convertToEntity(abstractUserDTO);
		 //proveri da li postoji kao korisnik u sistemu
		 if(user.getIsUnregistered()) {
			 //ako ne, snimi ga 
			 abstractUserService.save(user);
		 }
		 
		 //podesi na kartu njega kao vlasnika
		 flightTicket.setAbstractUser(user);
		 
		 
		 //bitno je da taj korisnik i postoji kao registrovan, ako nije onda preskociti slanje mejla
		 if(user.getIsUnregistered()) {
			 return new ResponseEntity<>(new StringJSON("Successfuly reserved it, congratulations"), HttpStatus.NOT_FOUND);
		 }
		 
		 
		 //ako je korisnik za koga se rezervise razlicita od trenutnog korisnika, poslati mu mejl		 
		 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println("Ulogovani je : " + loggedInUser.getName());
		 AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		 
		 if(!user.equals(loggedUser)) {
			 //TODO: @Viktor ovde napraviti link ka statickoj adresi sa listom pozivnica za korisnika koji treba da primi mejl
			 String initationLink = "https://www.google.com/";
			 user.setEmail("tviksi96@gmail.com");
			 flightTicketService.sendEmailReservationInvitation(loggedUser, user, initationLink, flightTicket.getId());
		 }
		 
		 return new ResponseEntity<>(new StringJSON("Successfuly reserved it, congratulations"), HttpStatus.NOT_FOUND);

	}
	
	
	/**
	 * Metoda za prikaz svih rezervisanih karata neke aviokompanije
	 */
	@RequestMapping(value = "/tickets/{airline_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> getAllAirlineFlightTicket(
			@PathVariable Long airline_id,
			@RequestParam(name="isFastReservation", required = false) Boolean isFastReservation,
			Pageable pageRequest){
		 List<FlightTicket> flightTickets = new ArrayList<>();
		 try {
			 flightTickets = flightTicketService.findAllByFlight_Airline_Id(airline_id,pageRequest);
		 }
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
		 }
		 
		 List<FlightTicketDTO> flightTicketDTOs = new ArrayList<>();
		 
		 for (FlightTicket flightTicket : flightTickets) {
			FlightTicketDTO flightTicketDTO = flightTicketService.convertToDTO(flightTicket);
			flightTicketDTOs.add(flightTicketDTO);
		 }
		 
		 return new ResponseEntity<>(flightTicketDTOs, HttpStatus.NOT_FOUND);

	}
	
	
	/**
	 * Metoda za listing svih karata nekog leta
	 */	
	@RequestMapping(value = "/tickets/{flight_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> getAllFlightTicketFromOneFlight(
			@PathVariable Long flight_id,
			@RequestParam(name="isFastReservation", required = false) Boolean isFastReservation,
			Pageable pageRequest)
	
	{
		 Page<FlightTicket> flightTickets;
		 try {
			 flightTickets = flightTicketService.findAllByFlight_Id(flight_id, pageRequest);
		 }
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(new StringJSON("Flight ticket not found"), HttpStatus.NOT_FOUND);
		 }
		 
		 List<FlightTicketDTO> flightTicketDTOs = new ArrayList<>();
		 for (FlightTicket flightTicket : flightTickets) {
			FlightTicketDTO flightTicketDTO = flightTicketService.convertToDTO(flightTicket);
			flightTicketDTOs.add(flightTicketDTO);
		 }
			 
		 Page<FlightTicketDTO> ret=  new PageImpl<>(flightTicketDTOs, pageRequest, flightTickets.getTotalElements());
		 return ResponseEntity.ok(ret);
		 //return new ResponseEntity<>(flightTicketDTOs, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Metoda za ocenjivanje aviokopmanije
	 */
	@RequestMapping(value = "/grade/airline",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> gradeAirline(@RequestBody FlightTicketDTO flightTicketDTO)
	{
		FlightTicket flightTicket = flightTicketService.convertToEntity(flightTicketDTO);
		//uzme se ocena aviokopmanije
		Integer airlineGrade = flightTicket.getAirlineGrade();
		 
		//da li je u rasponu 1-5
		if( 1 <= airlineGrade && airlineGrade <= 5) {
			//ako jeste unesi je u aviokompaniju tj za sabiranje
			Airline airline = flightTicket.getFlight().getAirline();
			airline.setGradeSum(airline.getGradeSum() + airlineGrade);
			airline.setGradeCount(airline.getGradeCount() + 1);
			//sacuvaj tu aviokompaniju		
			airlineService.saveAirline(airline);
		}
		
		
		
		//uzme se ocena leta
		Integer flightGrade = flightTicket.getFlightGrade();
		
		//da li je u rasponu 1-5
		if( 1 <= flightGrade && flightGrade <= 5) {
			Flight flight = flightTicket.getFlight();
			flight.setGradeSum(flight.getGradeSum() + flightGrade);
			flight.setGradeCount(flight.getGradeCount() + 1);
			
			
			flightService.saveFlight(flight);
		}
		
		//sacuvaj kartu	 
		 
		flightTicketService.saveFlightTicket(flightTicket);
		 
		return new ResponseEntity<>(new StringJSON("Successfully done grading"), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/grade/flight",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)			
	public ResponseEntity<?> gradeFlight(@RequestBody FlightTicketDTO flightTicketDTO)
	{
		FlightTicket flightTicket = flightTicketService.convertToEntity(flightTicketDTO);
				
		//uzme se ocena leta
		Integer flightGrade = flightTicket.getFlightGrade();
		
		//da li je u rasponu 1-5
		if( 1 <= flightGrade && flightGrade <= 5) {
			Flight flight = flightTicket.getFlight();
			flight.setGradeSum(flight.getGradeSum() + flightGrade);
			flight.setGradeCount(flight.getGradeCount() + 1);
			
			
			flightService.saveFlight(flight);
		}
		
		//sacuvaj kartu	 
		 
		flightTicketService.saveFlightTicket(flightTicket);
		 
		return new ResponseEntity<>(new StringJSON("Successfully done grading"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Metoda za prikaz prodatih karata na dnevnom,nedeljnom i mesecnom nivou <br>
	 * Vraca niz od 3 niza za odabranu godinu
	 * @param year - na osnovu odabrane godine prikazuje
	 */
	
	
	

	/**
	 * Metoda za prikaz prodatih prihoda na dnevnom,nedeljnom i mesecnom nivou <br>
	 * Vraca niz od 3 niza za odabranu godinu
	 * @param year - na osnovu odabrane godine prikazuje
	 */
	
}