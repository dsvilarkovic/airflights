package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "brand", nullable = false)
	private String brand;
	
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "year", nullable = false)
	private int year;
	
	@Column(name = "seats", nullable = false)
	private int seats;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "reserved", nullable = false)
	private Boolean reserved;

	@ManyToOne 
	private BranchLocations branch_locations;
	
	public Vehicle() {
		//super();
		// TODO Auto-generated constructor stub
	}



	public Vehicle(Long id, String name, String brand, String model, int year, int seats, String type, int rating, double price, Boolean r) {
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
		this.reserved = r;
	}
	
	public Vehicle(Vehicle v) {
		this(v.getId(),v.getName(),v.getBrand(),v.getModel(),v.getYear(),v.getSeats(),v.getType(),v.getRating(),v.getPrice(),v.getReserved());
	}



	public Boolean getReserved() {
		return reserved;
	}



	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
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
	
	/*public RentACar getRentacar() {
		return rentacar;
	}

	public void setRentacar(RentACar rentacar) {
		this.rentacar = rentacar;
	}*/



	public BranchLocations getBranch_locations() {
		return branch_locations;
	}



	public void setBranch_locations(BranchLocations branch_locations) {
		this.branch_locations = branch_locations;
	}
	
	
	
	
}
