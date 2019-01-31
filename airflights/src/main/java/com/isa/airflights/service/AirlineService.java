package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Airline;
import com.isa.airflights.repository.AirlineRepository;

@Service
public class AirlineService {
	@Autowired
	AirlineRepository airlineRepository;
	
	
	
	public void addAirline(Airline airline) {
		airlineRepository.save(airline);		
	}
	
	public boolean updateAirline(Airline airline) {
		try {
			Airline foundAirline = airlineRepository.getOne(airline.getId());
			
			airline.setAirplanes(foundAirline.getAirplanes());
			airline.setDestinations(foundAirline.getDestinations());
			airline.setFastDiscountedTickets(foundAirline.getFastDiscountedTickets());
			airline.setFlights(foundAirline.getFlights());
			airline.setLuggageClassPriceList(foundAirline.getLuggageClassPriceList());
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		airlineRepository.save(airline);		
		return true;
	}
	
	public boolean deleteAirline(Long id) {
		try {
			airlineRepository.deleteById(id);
		}
		catch(IllegalArgumentException exception) {
			return false;
		}
		return true;
	}
	
	public Airline getAirline(Long id) {
		return airlineRepository.getOne(id);
	}
	
	public List<Airline> findAllAirlines(){
		return airlineRepository.findAll();
	}
	
	public Page<Airline> findAirlinesByPage(Pageable pageRequest){
		return airlineRepository.findAll(pageRequest);
	}

}
