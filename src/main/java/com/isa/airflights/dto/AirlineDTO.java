package com.isa.airflights.dto;

import com.isa.airflights.model.Airline;

public class AirlineDTO {
	private Long id;
	private String fullName;
	private String promoInfo;
	private Double longitude;
	private Double latitude;
	private LuggagePriceListDTO luggageClassPriceList;
	
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
	public String getPromoInfo() {
		return promoInfo;
	}
	public void setPromoInfo(String promoInfo) {
		this.promoInfo = promoInfo;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public LuggagePriceListDTO getLuggageClassPriceList() {
		return luggageClassPriceList;
	}
	public void setLuggageClassPriceList(LuggagePriceListDTO luggageList) {
		this.luggageClassPriceList = luggageList;
	}
	public AirlineDTO(Airline a) {
		this.id = a.getId();
		this.fullName = a.getFullName();
		this.promoInfo = a.getPromoInfo();
		this.longitude = a.getLocation().getLongitude();
		this.latitude = a.getLocation().getLatitude();
		this.luggageClassPriceList = new LuggagePriceListDTO(a.getLuggagePriceList());
	
	}
	public AirlineDTO() {}
	
	

}
