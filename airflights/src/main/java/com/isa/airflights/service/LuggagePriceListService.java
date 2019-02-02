package com.isa.airflights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.LuggagePriceList;
import com.isa.airflights.repository.LuggagePriceListRepository;

@Service
public class LuggagePriceListService {

	@Autowired
	LuggagePriceListRepository luggagePriceListRepository;
	
	
	public LuggagePriceList getLuggagePriceList(Long id) {
		return luggagePriceListRepository.getOne(id);
	}
}
