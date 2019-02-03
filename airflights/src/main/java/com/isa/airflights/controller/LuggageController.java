package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.isa.airflights.dto.LuggagePriceDTO;
import com.isa.airflights.model.LuggagePrice;
import com.isa.airflights.model.LuggagePriceList;
import com.isa.airflights.service.LuggagePriceListService;
import com.isa.airflights.service.LuggageService;
import com.isa.airflights.utils.StringJSON;

/**
 * Kontroler za crud operacije nad prtljazima za jednu aviokompaniju
 * @author dusan
 *
 */
@RestController
@RequestMapping("api/luggage")
@CrossOrigin(origins = "http://localhost:4200")
public class LuggageController {

	@Autowired
	private LuggageService luggageService;
	
	@Autowired 
	private LuggagePriceListService luggagePriceListService;
	
	@Autowired 
	private ModelMapper modelMapper;
		
	@RequestMapping(value = "/add",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addLuggage(@RequestBody LuggagePriceDTO luggagePriceDTO){
		LuggagePrice luggagePrice = convertToEntity(luggagePriceDTO);
		luggageService.addLuggagePrice(luggagePrice, luggagePriceDTO.getLuggagePriceList_id());
		
		return new ResponseEntity<>(new StringJSON("Successfully created new luggage!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLuggage(@PathVariable Long id){
		LuggagePrice luggagePrice;
		try {
			luggagePrice = luggageService.getLuggagePrice(id);
		}
		catch(EntityNotFoundException e) {
			return new ResponseEntity<>(new StringJSON("No such luggage found!"), HttpStatus.NOT_FOUND);
		}
		LuggagePriceDTO luggagePriceDTO = convertToDTO(luggagePrice);
		return new ResponseEntity<LuggagePriceDTO>(luggagePriceDTO, HttpStatus.OK);
	}
	
	/**
	 * Metoda za brisanje prtljaga
	 * @param id - id prtljaga koji ce se birsati
	 * @return - uspesnost brisanja
	 */
	@RequestMapping(
				value = "/delete/{id}",
				method = RequestMethod.DELETE,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> deleteLuggage(@PathVariable Long id){
		Boolean sucess = luggageService.deleteLuggagePrice(id);
		
		if(sucess) {
			return new ResponseEntity<>(new StringJSON("Luggage successfully deleted!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("No luggage found to be deleted"), HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> updateLuggage(@RequestBody LuggagePriceDTO luggagePriceDTO){
		LuggagePrice luggagePrice= convertToEntity(luggagePriceDTO);
		
		Boolean success = luggageService.updateLuggagePrice(luggagePrice);
		if(success) {
			return new ResponseEntity<>(new StringJSON("Successfully updated luggage!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new StringJSON("Error, no such luggage found!"), HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Metoda za listanje svih prtljaga
	 * @return
	 */
	@RequestMapping(
				value = "/find/all",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)		
	public ResponseEntity<?> findAllLuggagePrices(){
		List<LuggagePrice> luggagePrices = luggageService.findAll();
		
		List<LuggagePriceDTO> luggagePriceDTOs = new ArrayList<>();
		for (LuggagePrice luggagePrice: luggagePrices) {
			LuggagePriceDTO luggagePriceDTO = convertToDTO(luggagePrice);
			luggagePriceDTOs.add(luggagePriceDTO);
		}
		
		return new ResponseEntity<>(luggagePriceDTOs, HttpStatus.OK);
	}
	public LuggagePrice convertToEntity(LuggagePriceDTO luggagePriceDTO) {
		LuggagePrice luggagePrice = modelMapper.map(luggagePriceDTO, LuggagePrice.class);
		LuggagePriceList luggagePriceList = luggagePriceListService.getLuggagePriceList(luggagePriceDTO.getLuggagePriceList_id());
		luggagePrice.setLuggagePriceList(luggagePriceList);
		return luggagePrice;
	}
	
	public LuggagePriceDTO convertToDTO(LuggagePrice luggagePrice) {
		LuggagePriceDTO luggagePriceDTO = modelMapper.map(luggagePrice, LuggagePriceDTO.class);
		luggagePriceDTO.setLuggagePriceList_id(luggagePrice.getLuggagePriceList().getId());
		return luggagePriceDTO;
	}
}
