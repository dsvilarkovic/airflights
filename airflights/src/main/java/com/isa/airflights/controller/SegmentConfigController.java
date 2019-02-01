package com.isa.airflights.controller;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
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

import com.isa.airflights.dto.SegmentConfigDTO;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.model.SegmentConfig;
import com.isa.airflights.service.AirplaneService;
import com.isa.airflights.service.SegmentConfigService;
import com.isa.airflights.utils.StringJSON;

/**
 * Kontroler za kofiguraciju sedista
 * @author dusan
 *
 */
@RestController
@RequestMapping("api/seats")
@CrossOrigin(origins = "http://localhost:4200")
public class SegmentConfigController {

	@Autowired
	SegmentConfigService segmentConfigService;
	
	@Autowired
	AirplaneService airplaneService;
	
	@Autowired 
	ModelMapper modelMapper;
	
	//obrati paznju na cuvanje seats i airplane pri azuriranju
	
	//citanje
	/**
	 * Metoda za citanje trenutno podrzane konfiguracije
	 * @param id
	 * @return
	 */
	@RequestMapping(
				value = "/get/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> getSegmentConfig(@PathVariable Long id){
		SegmentConfig segmentConfig;
		try {
			segmentConfig = segmentConfigService.getConfig(id);
		}
		catch(EntityNotFoundException e) {
			return new ResponseEntity<>(new StringJSON("No such configuration found"), HttpStatus.NOT_FOUND); 
		}
		SegmentConfigDTO segmentConfigDTO = convertToDTO(segmentConfig);
		return new ResponseEntity<>(segmentConfigDTO, HttpStatus.OK); 
	}
	
	
	//dodavanje
	@RequestMapping(
			value = "/add",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> addConfiguration(@RequestBody SegmentConfigDTO segmentConfigDTO){
		SegmentConfig segmentConfig = convertToEntity(segmentConfigDTO);
		
		//pri dodavanju segmenta potrebno navesti i kod airplane koja mu je konfiguracija
		Airplane airplane = airplaneService.findOne(segmentConfigDTO.getAirplane_id());
		airplane.setSegmentConfig(segmentConfig);
		airplaneService.updateAirplane(airplane);
		
		//podesi i kod segmenta ovu informaciju
		segmentConfig.setAirplane(airplane);
		
		segmentConfigService.addConfig(segmentConfig);
		
		return new ResponseEntity<>(new StringJSON("New configuration successfully added"), HttpStatus.OK);
	}
	
	/**
	 * Metoda za brisanje konfigauracije
	 * @param id
	 * @return
	 */
	@RequestMapping(
			value = "/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> deleteConfiguration(@PathVariable Long id){
		try {
			segmentConfigService.getConfig(id);
		}
		catch(EntityNotFoundException exception) {
			return new ResponseEntity<StringJSON>(new StringJSON("Error, no such configuration found"), HttpStatus.NOT_FOUND);
		}
		
		Boolean success = segmentConfigService.deleteConfig(id);
		
		if(success) {
			return new ResponseEntity<>(new StringJSON("Configuration is successfully deleted!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("Error, no such config parameter found!"), HttpStatus.NOT_FOUND);
	}
	
	
	
	/**
	 * Azuriranje konfiguracije segmenata
	 * @param segmentConfigDTO
	 * @return
	 */
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> updateConfiguration(@RequestBody SegmentConfigDTO segmentConfigDTO){
		SegmentConfig segmentConfig = convertToEntity(segmentConfigDTO);
		
		Boolean success = segmentConfigService.updateConfig(segmentConfig);
		if(success) {
			return new ResponseEntity<>(new StringJSON("Successfully updated this configuration!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("Error, no such configuration found to be updated!"), HttpStatus.NOT_FOUND);
	}
	
	
	private SegmentConfigDTO convertToDTO(SegmentConfig segmentConfig) {
		SegmentConfigDTO segmentConfigDTO = modelMapper.map(segmentConfig, SegmentConfigDTO.class);
		segmentConfigDTO.setAirplane_id(segmentConfig.getAirplane().getId());
		return segmentConfigDTO;
	}
	
	private SegmentConfig convertToEntity(SegmentConfigDTO segmentConfigDTO) {
		SegmentConfig segmentConfig = modelMapper.map(segmentConfigDTO, SegmentConfig.class);
		return segmentConfig;
	}
}
