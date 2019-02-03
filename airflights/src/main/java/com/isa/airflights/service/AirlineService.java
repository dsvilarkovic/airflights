package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Airline;
import com.isa.airflights.model.LuggagePriceList;
import com.isa.airflights.repository.AirlineRepository;
import com.isa.airflights.repository.LuggagePriceListRepository;

@Service
public class AirlineService {
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private LuggagePriceListRepository luggagePriceListRepository;
	
	
	
	public void addAirline(Airline airline) {
		
		airlineRepository.save(airline);		
		
		LuggagePriceList luggagePriceList = new LuggagePriceList();
		//podesi sa obe strane
		airline.setLuggagePriceList(luggagePriceList);
		luggagePriceList.setAirline(airline);
		
		//snimi prtljag
		luggagePriceListRepository.save(luggagePriceList);
	}
	
	public Boolean updateAirline(Airline airline) {
		Airline foundAirline;
		try {
			foundAirline = airlineRepository.getOne(airline.getId());
			
			foundAirline.setFullName(airline.getFullName());
			foundAirline.setPromoInfo(airline.getPromoInfo());
			airlineRepository.save(foundAirline);		
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		
		return true;
	}
	
	public Boolean deleteAirline(Long id) {
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
