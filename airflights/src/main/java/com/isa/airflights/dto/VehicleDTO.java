package com.isa.airflights.dto;

import com.isa.airflights.model.Vehicle;

public class VehicleDTO {

	private Long id;
	private String name;
	
	private String brand;
	
	private String model;
	
	private int year;
	
	private int seats;
	
	private String type;
	
	private double rating;

	private double price;
	
	private boolean reserved;
	
	private Long branchLocationId;
	
	private Long rentACarId;
	
	

	public VehicleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public VehicleDTO(Long id, String name, String brand, String model, int year, int seats, String type, double rating,
			double price, boolean reserved) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.seats = seats;
		this.type = type;
		this.rating = rating;
		this.price = price;
		this.reserved = reserved;
	}

	
	public VehicleDTO(Vehicle v) {
		this(v.getId(),v.getName(),v.getBrand(),v.getModel(),v.getYear(),v.getSeats(),v.getType(),v.getRating(),v.getPrice(),v.getReserved());
		this.branchLocationId = v.getBranch_locations().getId();
		this.rentACarId = v.getRentacar().getId();
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



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public int getSeats() {
		return seats;
	}



	public void setSeats(int seats) {
		this.seats = seats;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public boolean isReserved() {
		return reserved;
	}



	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}



	public Long getBranchOffice_id() {
		return branchLocationId;
	}



	public void setBranchOffice_id(Long branchLocationId) {
		this.branchLocationId = branchLocationId;
	}



	public Long getRentACarId() {
		return rentACarId;
	}



	public void setRentACarId(Long rentACarId) {
		this.rentACarId = rentACarId;
	}
	
	
	
	
	
	
}