package com.isa.airflights.controller;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.SeatDTO;
import com.isa.airflights.dto.SegmentConfigDTO;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.model.Seat;
import com.isa.airflights.model.SegmentConfig;
import com.isa.airflights.service.AirplaneService;
import com.isa.airflights.service.SeatService;
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
	SeatService seatService;
	
	@Bean
	public SeatService getSeatService() {
		return new SeatService();
	}
	
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
		
		Airplane airplane = airplaneService.findOne(segmentConfigDTO.getAirplane_id());
		
		
		//podesi za koji avion se odnosi
		segmentConfig.setAirplane(airplane);		
		segmentConfigService.addConfig(segmentConfig);
		
		
		//pri dodavanju segmenta potrebno navesti i kod airplane koja mu je konfiguracija		
		airplane.setSegmentConfig(segmentConfig);
		airplaneService.updateAirplane(airplane);
		
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
		
		Set<Seat> seats = segmentConfig.getSeats();
		for (Seat seat : seats) {
			SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);
			seat.setSegmentConfig(segmentConfig);
			
			seatDTO.setConfiguration(segmentConfig.getId());
			segmentConfigDTO.getSeatDTOs().add(seatDTO);
		}
		return segmentConfigDTO;
	}
	
	private SegmentConfig convertToEntity(SegmentConfigDTO segmentConfigDTO) {
		SegmentConfig segmentConfig = modelMapper.map(segmentConfigDTO, SegmentConfig.class);
		
		
		Set<SeatDTO> seatDTOs = segmentConfigDTO.getSeatDTOs();
		for (SeatDTO seatDTO : seatDTOs) {
			Seat seat = new Seat();
			seat = modelMapper.map(seatDTO, Seat.class);
			System.out.println("Segment num je: " + seatDTO.getSegment_num() + " | " + seat.getSegment_num());
			
			seatService.saveSeat(seat);			
			//System.out.println("Id od seat je "  + seat.getId());
			//OVDE ubaciti seatService da radi ono sto je u segmentConfigService radjeno
			
			
			seat.setSegmentConfig(segmentConfig);			
			segmentConfig.getSeats().add(seat);
		}
		
		return segmentConfig;
	}
}
