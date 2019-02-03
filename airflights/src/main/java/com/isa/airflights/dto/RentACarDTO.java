package com.isa.airflights.dto;

import java.util.ArrayList;
import java.util.List;

import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.model.RentACar;

public class RentACarDTO {

	
	private Long id;
	private String name;
	private String address;
	private String city;
	private String description;
	private List<BranchLocationsDTO> branches;
	private double ratingsCount;
	private double ratingsSum;

	
	
	
	
	public RentACarDTO(Long id, String name,String address, String city, String description, double rating,double sum) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
		this.ratingsCount = rating;
		this.ratingsSum = sum;

		
	}
	
	public RentACarDTO(RentACar rent) {
		
		this(rent.getId(),rent.getName(), rent.getAddress(), rent.getCity(), rent.getDescription(),rent.getRatingsCount(),rent.getRatingsSum());
		
		branches =new ArrayList<>();
		for(BranchLocations branch : rent.getBranch_locations()){
			System.out.println("Lista filijala: " + branch.getAddress());
			this.branches.add(new BranchLocationsDTO(branch));
		}
		
	}
	
	
	

	public double getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(double ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public double getRatingsSum() {
		return ratingsSum;
	}

	public void setRatingsSum(double ratingsSum) {
		this.ratingsSum = ratingsSum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<BranchLocationsDTO> getBranches() {
		return branches;
	}
	public void setBranches(List<BranchLocationsDTO> branches) {
		this.branches = branches;
	}
	
	
	
}
