package com.isa.airflights.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.isa.airflights.model.LuggagePriceList;

public class LuggagePriceListDTO {

	public Set<LuggagePriceDTO> luggageClassPrices = new HashSet<>();
	
	public LuggagePriceListDTO() {
	
	}
	public LuggagePriceListDTO(LuggagePriceList lpl) {
		this.luggageClassPrices =  lpl.getLuggagePrices().stream().map(lp -> new LuggagePriceDTO(lp)).collect(Collectors.toSet());;
	}
}
