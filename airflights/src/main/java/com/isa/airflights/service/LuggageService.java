package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.LuggagePrice;
import com.isa.airflights.model.LuggagePriceList;
import com.isa.airflights.repository.LuggagePriceListRepository;
import com.isa.airflights.repository.LuggagePriceRepository;

/**
 * Servis za dodavanje prtljaga u aviokompanije
 * @author dusan
 *
 */
@Service
public class LuggageService {

	@Autowired
	LuggagePriceRepository luggagePriceRepository;  
	
	@Autowired 
	LuggagePriceListRepository luggagePriceListRepository;
	
	public void addLuggagePrice(LuggagePrice luggagePrice, Long luggagePriceListId) {
		LuggagePriceList luggagePriceList = luggagePriceListRepository.getOne(luggagePriceListId);
		luggagePrice.setLuggagePriceList(luggagePriceList);
		luggagePriceRepository.save(luggagePrice);
	}
	
	
	public Boolean updateLuggagePrice(LuggagePrice luggagePrice) {
		LuggagePrice foundLuggagePrice;
		try {
			foundLuggagePrice = luggagePriceRepository.getOne(luggagePrice.getId());
			
			foundLuggagePrice.setHeight(luggagePrice.getHeight());
			foundLuggagePrice.setLength(luggagePrice.getLength());
			foundLuggagePrice.setWidth(luggagePrice.getWidth());
			foundLuggagePrice.setWeight(luggagePrice.getWeight());
			foundLuggagePrice.setPrice(luggagePrice.getPrice());
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		luggagePriceRepository.save(foundLuggagePrice);
		return true;
	}
	
	public Boolean deleteLuggagePrice(Long id) {
		try {
			luggagePriceRepository.getOne(id);
			luggagePriceRepository.deleteById(id);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		
		return true;
	}
	
	
	public LuggagePrice getLuggagePrice(Long id) {
		return luggagePriceRepository.getOne(id);
	}
	
	
	public List<LuggagePrice> findAll(){
		return luggagePriceRepository.findAll();
	}
}
