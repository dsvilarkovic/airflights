package com.isa.airflights.dto;

import java.util.ArrayList;
import java.util.List;

import com.isa.airflights.model.BranchLocations;
import com.isa.airflights.model.Vehicle;

public class BranchLocationsDTO {

	private Long id;
	
	private String address;
	
	private String city;
	
	private List<VehicleDTO> vehicles;
	
	private Long rentACarId;
	
	public BranchLocationsDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BranchLocationsDTO(BranchLocations b) {
		this.id = b.getId();
		this.address = b.getAddress();
		this.city = b.getCity();
		this.rentACarId = b.getRentacar().getId();
		vehicles = new ArrayList<>();
		
		for(Vehicle v :  b.getVehicles()) {
			this.vehicles.add((new VehicleDTO(v)));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<VehicleDTO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleDTO> vehicles) {
		this.vehicles = vehicles;
	}

	public Long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(Long rentACarId) {
		this.rentACarId = rentACarId;
	}
	
	
}
