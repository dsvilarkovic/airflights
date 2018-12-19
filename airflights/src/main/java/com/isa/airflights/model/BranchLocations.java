package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BranchLocations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "city", nullable = true)
	private String city;
	
	@ManyToOne
	private RentACar rentacar;
	
	
	public BranchLocations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BranchLocations(Long id, String address, String city) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		
	}
	
	public BranchLocations(BranchLocations bl) {
		this(bl.getId(),bl.getAddress(),bl.getCity());
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

	public RentACar getRentacar() {
		return rentacar;
	}

	public void setRentacar(RentACar rentacar) {
		this.rentacar = rentacar;
	}
	
	
	
	
}
