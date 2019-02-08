package com.isa.airflights.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.isa.airflights.model.Hotel;

public class HotelDTO {
	

	private Long id;
	

	private String name;


	private String address;	
	

	private String description;
	

	private String city;
	

	private Long ratingsCount = 0L;
	

	private Long ratingsSum = 0L;
	
	public HotelDTO() {
		
	}


	public HotelDTO(Long id, String name, String address, String description, String city, Long ratingsCount,
			Long ratingsSum) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.city = city;
		this.ratingsCount = ratingsCount;
		this.ratingsSum = ratingsSum;
	}
	
	public HotelDTO(Hotel h) {
		this(h.getId(),h.getName(),h.getAddress(),h.getDescription(),h.getCity(),h.getRatingsCount(),h.getRatingsSum());
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Long getRatingsCount() {
		return ratingsCount;
	}


	public void setRatingsCount(Long ratingsCount) {
		this.ratingsCount = ratingsCount;
	}


	public Long getRatingsSum() {
		return ratingsSum;
	}


	public void setRatingsSum(Long ratingsSum) {
		this.ratingsSum = ratingsSum;
	}
	
	

}
