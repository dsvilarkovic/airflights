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

import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.service.AirlineService;
import com.isa.airflights.service.AirplaneService;
import com.isa.airflights.utils.StringJSON;

/**
 * Kontroler za upravljanje kreiranjem aviona i konfiguracijom sedista
 * @author dusan
 *
 */
@RestController
@RequestMapping(value="api/airline/airplane")
@CrossOrigin(origins = "http://localhost:4200")
//TODO AirplaneController: za svaki od delova kontrolera proveriti da li moze da se manipule avionom ako je od te aviokompanije
public class AirplaneController {
	
	
	
	
	@Autowired 
	private AirplaneService airplaneService;
	
	@Autowired
	private AirlineService airlineService;
	
//	@Bean
//	public ModelMapper getModelMapper() {
//		return new ModelMapper();
//	}
	
	
	/**
	 * Metoda za uzimanje aviona iz baze
	 * @return - AirplaneDTO - ako se nadje
	 * 		   - StringJSON - ako se desi greska 
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
					value = "/{id}",
					method = RequestMethod.GET)
	public ResponseEntity<?> getAirplane(@PathVariable Long id){		
		Airplane airplane;
		try {
			airplane = airplaneService.findOne(id);
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<StringJSON>(new StringJSON("Airplane not found"), HttpStatus.NOT_FOUND);
		}
		
		AirplaneDTO airplaneDTO = airplaneService.convertToDTO(airplane);
		return new ResponseEntity<AirplaneDTO>(airplaneDTO, HttpStatus.OK);
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
		Airplane airplane = airplaneService.convertToEntity(airplaneDTO);
		
		//podesi aviokompaniju
		System.out.println("Id je: " + airplaneDTO.getAirline_id());
		Airline airline = airlineService.getAirline(airplaneDTO.getAirline_id());
		
		airplane.setAirline(airline);
		
		airplaneService.createAirplane(airplane);
		
		return new ResponseEntity<StringJSON>(new StringJSON("Successfully created airplane!"), HttpStatus.OK);
	}
	
	
	/**
	 * Metoda za azuriranje aviona
	 * @param airplaneDTO - podatak o azuriranom avionu
	 * @return - String oblik problema 
	 */
	@RequestMapping(value = "/update",
					method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateAirplane(@RequestBody AirplaneDTO airplaneDTO){
		Airplane airplane = airplaneService.convertToEntity(airplaneDTO);
		
		//prvo proveriti postoji li ovakav avion u bazi
		Boolean success = airplaneService.updateAirplane(airplane);
		if(success) {
			return new ResponseEntity<StringJSON>(new StringJSON("Successfully updated airplane!"), HttpStatus.OK);
		}
		return new ResponseEntity<StringJSON>(new StringJSON("Error, no such airplane found"), HttpStatus.NOT_FOUND);	
	}
	
	
	/**
	 * Metoda za brisanje instance aviona
	 * @param id - id aviona koji ce se brisati
	 * @return - uspesnost u {@link SpringJSON} formatu
	 */
	@RequestMapping(value = "/delete/{id}",
					method = RequestMethod.DELETE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAirplane(@PathVariable Long id){
		//prvo proveri postoji li
		try {
			airplaneService.findOne(id);
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<StringJSON>(new StringJSON("Error, no such airplane found"), HttpStatus.NOT_FOUND);
		}
		
		Boolean success = airplaneService.deleteAirplane(id);	
		if(success) {
			return new ResponseEntity<StringJSON>(new StringJSON("Successfully deleted this airplane"), HttpStatus.OK);
		}
		return new ResponseEntity<StringJSON>(new StringJSON("Error, wrong delete parameter has been sent "), HttpStatus.NOT_FOUND);
	}
	

	
	@RequestMapping(value = "/find/all",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAirplanesByPageNumber(Pageable pageRequest){
		Page<Airplane> page = airplaneService.findByPageNumber(pageRequest);
		
		List<AirplaneDTO> airplaneDTOs = new ArrayList<>();
		
		for (Airplane airplane : page) {
			AirplaneDTO airplaneDTO = airplaneService.convertToDTO(airplane);
			airplaneDTOs.add(airplaneDTO);
		}
		
		Page<AirplaneDTO> ret = new PageImpl<>(airplaneDTOs, pageRequest, page.getTotalElements());
		return ResponseEntity.ok(ret);
		//return new ResponseEntity<>(airplaneDTOs, HttpStatus.OK);
	}
	
	
	

}