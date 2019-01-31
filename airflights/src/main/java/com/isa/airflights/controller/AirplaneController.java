package com.isa.airflights.controller;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.model.Airplane;
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
	ModelMapper modelMapper;
	
	@Autowired 
	AirplaneService airplaneService;
	
		
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
		
		AirplaneDTO airplaneDTO = convertToDTO(airplane);
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
		Airplane airplane = convertToEntity(airplaneDTO);
		
		//TODO: po trenutno ulogovanom adminu aviokompanije proveriti id aviokompanije u avionu, mozda nisu isti
		
		
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
		Airplane airplane = convertToEntity(airplaneDTO);
		
		//prvo proveriti postoji li ovakav avion u bazi
		Boolean success = airplaneService.updateAirplane(airplane);
		if(success == false) {
			return new ResponseEntity<StringJSON>(new StringJSON("Error, no such airplane found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<StringJSON>(new StringJSON("Successfully updated airplane!"), HttpStatus.OK);
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
	public ResponseEntity<?> getAllAirplanes(){
		List<Airplane> airplanes =  airplaneService.findAll();		
		
		List<AirplaneDTO> airplaneDTOs = new ArrayList<>();
		for(Airplane airplane : airplanes) {
			AirplaneDTO airplaneDTO = convertToDTO(airplane);
			airplaneDTOs.add(airplaneDTO);
		}
		return new ResponseEntity<List<Airplane>>(airplanes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/find/page/",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAirplanesByPageNumber(Pageable pageRequest){
		Page<Airplane> page = airplaneService.findByPageNumber(pageRequest);
		
		List<AirplaneDTO> airplaneDTOs = new ArrayList<>();
		
		for (Airplane airplane : page) {
			AirplaneDTO airplaneDTO = convertToDTO(airplane);
			airplaneDTOs.add(airplaneDTO);
		}
		
		return new ResponseEntity<>(airplaneDTOs, HttpStatus.OK);
	}
	
	
	private AirplaneDTO convertToDTO(Airplane airplane) {
		AirplaneDTO airplaneDTO = modelMapper.map(airplane, AirplaneDTO.class);
		airplaneDTO.setAirline_id(airplane.getAirline().getId());
		airplaneDTO.setConfiguration(airplane.getSegmentConfig().getId());
		return airplaneDTO;
	}
	
	private Airplane convertToEntity(AirplaneDTO airplaneDTO) {
		Airplane airplane = modelMapper.map(airplaneDTO, Airplane.class);
		return airplane;
	}

}
