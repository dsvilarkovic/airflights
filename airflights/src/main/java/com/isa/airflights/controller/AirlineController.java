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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AirlineDTO;
import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.dto.AirportDestinationDTO;
import com.isa.airflights.dto.FlightDTO;
import com.isa.airflights.dto.LuggagePriceDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.LuggagePrice;
import com.isa.airflights.service.AirlineService;
import com.isa.airflights.service.AirplaneService;
import com.isa.airflights.service.AirportDestinationService;
import com.isa.airflights.service.FlightService;
import com.isa.airflights.service.LuggageService;
import com.isa.airflights.utils.StringJSON;

@RestController
@RequestMapping("api/airline")
@CrossOrigin(origins = "http://localhost:4200")
public class AirlineController {

		@Autowired
		private AirlineService airlineService;
		
		@Autowired
		private AirplaneService airplaneService;
		
		
		@Autowired
		private AirportDestinationService airportDestinationService;
		
		
		@Autowired 
		private LuggageService luggageService;
		
		@Autowired
		private FlightService flightService;
		
		
		
		
		
		/**
		 * Metoda za dodavanje aviokompanije		
		 * @param airlineDTO - aviokompanija koja se dodaje
		 * @return - response o uspesnosti operacije
		 */
		@RequestMapping(value = "/add",
						method = RequestMethod.POST,
						produces = MediaType.APPLICATION_JSON_VALUE,
						consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> addAirline(@RequestBody AirlineDTO airlineDTO){
			Airline airline = airlineService.convertToEntity(airlineDTO);
			airlineService.addAirline(airline);
			
			return new ResponseEntity<>(new StringJSON("Successfully created new airline!"), HttpStatus.OK);
		}
		
		/**
		 * Metoda za uzimanje aviokompanije		
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "/get/{id}",
						method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE
						)
		public ResponseEntity<?> getAirline(@PathVariable Long id){
			Airline airline;
			try {
				airline = airlineService.getAirline(id);
			}
			catch(EntityNotFoundException exception) {
				return new ResponseEntity<>(new StringJSON("No such airline found"), HttpStatus.NOT_FOUND);
			}
			
			AirlineDTO airlineDTO = airlineService.convertToDTO(airline);
			return new ResponseEntity<>(airlineDTO, HttpStatus.OK);
		}
		
		/**
		 * Metoda za brisanje aviokopmanije
		 * @param id - id aviokompanije koja ce se brisati
		 * @return - uspesnost brisanja
		 */
		@RequestMapping(
					value = "/delete/{id}",
					method = RequestMethod.DELETE,
					produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<?> deleteAirline(@PathVariable Long id){
			Boolean sucess = airlineService.deleteAirline(id);
			
			if(sucess) {
				return new ResponseEntity<>(new StringJSON("Airline successfully deleted!"), HttpStatus.OK);
			}
			return new ResponseEntity<>(new StringJSON("No airline found to be deleted"), HttpStatus.NOT_FOUND);
		}
		
		
		/**
		 * Azuriranje aviokompanije
		 * @param airlineDTO
		 * @return
		 */
		@RequestMapping(
					value = "/update",
					method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<?> updateAirline(@RequestBody AirlineDTO airlineDTO){
			Airline airline = airlineService.convertToEntity(airlineDTO);
			
			Boolean success = airlineService.updateAirline(airline);
			if(success) {
				return new ResponseEntity<>(new StringJSON("Successfully updated airline!"), HttpStatus.OK);
			}
			return new ResponseEntity<>(new StringJSON("Error, no such airline found!"), HttpStatus.NOT_FOUND);
		}
		
		
		/**
		 * Metoda za vracanje svih aviokompanija
		 * @param pageRequest
		 * @return
		 */
		@RequestMapping(
					value = "/find/all",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<?> findAirlinesByPage(Pageable pageRequest){
			Page<Airline> airlines = airlineService.findAirlinesByPage(pageRequest);
			
			List<AirlineDTO> airlineDTOs = new ArrayList<>();
			
			for(Airline airline: airlines) {
				AirlineDTO airlineDTO = airlineService.convertToDTO(airline);
				airlineDTOs.add(airlineDTO);
			}
			
			Page<AirlineDTO> ret = new PageImpl<>(airlineDTOs, pageRequest, airlines.getTotalElements());
			return ResponseEntity.ok(ret);
			//return new ResponseEntity<>(airlineDTOs, HttpStatus.OK);
		}
		
		/**
		 * Nalazi sve avione aviokompanije
		 * @param pageRequest
		 * @return
		 */
		@RequestMapping(
				value = "/find/airplanes/{airline_id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
		public ResponseEntity<?> findAirplanes(Pageable pageRequest, @PathVariable Long airline_id){
			List<Airplane> airplanes = airlineService.findAirplanes(pageRequest, airline_id).getContent();
			
			List<AirplaneDTO> airplaneDTOs = new ArrayList<>();
			
			for(Airplane airplane: airplanes) {
				AirplaneDTO airplaneDTO = airplaneService.convertToDTO(airplane);
				airplaneDTOs.add(airplaneDTO);
			}
			
			return new ResponseEntity<>(airplaneDTOs, HttpStatus.OK);
		}
		
		//TODO: nadji sve letove aviokompanije
		/**
		 * Nalazi sve letove aviokompanije
		 * @param pageRequest
		 * @return
		 */
		@RequestMapping(
				value = "/find/flights/{airline_id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
		public ResponseEntity<?> findFlights(Pageable pageRequest, @PathVariable Long airline_id){
			List<Flight> flights = airlineService.findFlights(pageRequest, airline_id).getContent();
			
			List<FlightDTO> flightDTOs = new ArrayList<>();
			
			for(Flight flight: flights) {
				FlightDTO flightDTO = flightService.convertToDTO(flight);
				flightDTOs.add(flightDTO);
			}
			
			return new ResponseEntity<>(flightDTOs, HttpStatus.OK);
		}
		
		/**
		 * Nadji sve prtljage aviokompanije
		 * @param pageRequest
		 * @param airline_id
		 * @return
		 */
		//TODO: nadji sve prtljage aviokopmanije
		@RequestMapping(
				value = "/find/luggage/{airline_id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
		public ResponseEntity<?> findLuggagePrices(Pageable pageRequest, @PathVariable Long airline_id){
			List<LuggagePrice> luggagePrices= airlineService.findLuggagePrices(pageRequest, airline_id).getContent();
			
			List<LuggagePriceDTO> luggagePriceDTOs = new ArrayList<>();
			
			for(LuggagePrice luggage: luggagePrices) {
				LuggagePriceDTO luggagePriceDTO = luggageService.convertToDTO(luggage);
				luggagePriceDTOs.add(luggagePriceDTO);
			}
			
			return new ResponseEntity<>(luggagePriceDTOs, HttpStatus.OK);
		}
		
		//TODO: nadji sve destinacije aviokopmanije
		/**
		 * Nadji sve destinacije aviokompanije
		 * @param pageRequest
		 * @param airline_id
		 * @return
		 */
		@RequestMapping(
				value = "/find/airport/{airline_id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
		public ResponseEntity<?> getAirportDestinations(Pageable pageRequest, @PathVariable Long airline_id){
			List<AirportDestination> airportDestinations= airlineService.findAirportDestinations(pageRequest, airline_id).getContent();
			
			List<AirportDestinationDTO> airportDestinationDTOs = new ArrayList<>();
			
			for(AirportDestination airportDestination: airportDestinations) {
				AirportDestinationDTO airportDestinationDTO = airportDestinationService.convertToDTO(airportDestination);
				airportDestinationDTOs.add(airportDestinationDTO);
			}
			
			return new ResponseEntity<>(airportDestinationDTOs, HttpStatus.OK);
		}
		
		
}
