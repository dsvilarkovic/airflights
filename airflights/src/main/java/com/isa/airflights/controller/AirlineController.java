package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
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
import com.isa.airflights.model.Airline;
import com.isa.airflights.service.AirlineService;
import com.isa.airflights.utils.StringJSON;

@RestController
@RequestMapping("api/airline")
@CrossOrigin(origins = "http://localhost:4200")
public class AirlineController {

		@Autowired
		AirlineService airlineService;
		
		@Autowired
		ModelMapper modelMapper;
		
		
		
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
		
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
			Airline airline = convertToEntity(airlineDTO);
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
			
			AirlineDTO airlineDTO = convertToDTO(airline);
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
		
		
		
		@RequestMapping(
					value = "/update",
					method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<?> updateAirline(@RequestBody AirlineDTO airlineDTO){
			Airline airline = convertToEntity(airlineDTO);
			
			Boolean success = airlineService.updateAirline(airline);
			if(success) {
				return new ResponseEntity<>(new StringJSON("Successfully updated airline!"), HttpStatus.OK);
			}
			return new ResponseEntity<>(new StringJSON("Error, no such airline found!"), HttpStatus.NOT_FOUND);
		}
		
		
		/**
		 * Metoda za listanje svih aviokompanije
		 * @return
		 */
		@RequestMapping(
					value = "/find/all",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE
				)		
		public ResponseEntity<?> findAllAirlines(){
			List<Airline> airlines = airlineService.findAllAirlines();
			
			List<AirlineDTO> airlineDTOs = new ArrayList<>();
			for (Airline airline : airlines) {
				AirlineDTO airlineDTO = convertToDTO(airline);
				airlineDTOs.add(airlineDTO);
			}
			
			return new ResponseEntity<>(airlineDTOs, HttpStatus.OK);
		}
		
		
		/**
		 * Metoda za vracanje strane 
		 * @param pageRequest
		 * @return
		 */
		@RequestMapping(
					value = "/find/page",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<?> findAirlinesByPage(Pageable pageRequest){
			Page<Airline> airlines = airlineService.findAirlinesByPage(pageRequest);
			
			List<AirlineDTO> airlineDTOs = new ArrayList<>();
			
			for(Airline airline: airlines) {
				AirlineDTO airlineDTO = convertToDTO(airline);
				airlineDTOs.add(airlineDTO);
			}
			
			return new ResponseEntity<>(airlineDTOs, HttpStatus.OK);
		}
		
		
		private AirlineDTO convertToDTO(Airline airline) {
			AirlineDTO airlineDTO = modelMapper.map(airline, AirlineDTO.class);
			if (airline.getLocation()!=null) {
				airlineDTO.setLongitude(airline.getLocation().getLongitude());
				airlineDTO.setLatitude(airline.getLocation().getLatitude());
			}
			return airlineDTO;
		}
	
		private Airline convertToEntity(AirlineDTO airlineDTO) {
			Airline airline = modelMapper.map(airlineDTO, Airline.class);
			return airline;
		}
}
