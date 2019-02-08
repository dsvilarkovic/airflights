package com.isa.airflights.dto;

import com.isa.airflights.model.AirportDestination;

public class AirportDestinationDTO {

	private Long id;
	private String fullName;
	
	public AirportDestinationDTO () {} 
	
	public AirportDestinationDTO (AirportDestination ad) {
		this.id = ad.getId();
		this.fullName = ad.getFullName();
	} 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}