package com.isa.airflights.dto;

public class AirlineDTO {
	private Long id;
	private String fullName;
	private String promoInfo;
	private Double longitude;
	private Double latitude;
	
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
	
	
	

}
