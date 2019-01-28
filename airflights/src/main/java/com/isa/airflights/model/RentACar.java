package com.isa.airflights.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RentACar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	/** @pdOid e0db3c3a-50d2-402c-b71c-973cbf62c53b */
	
	@Column(name = "address", nullable = true) 
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	
	@Column(name = "description", nullable = false)
	private String description;
	
	
	//prosecna ocena servisa
	@Column(name = "rating", nullable = false)
	private int rating;
	
	

	@OneToMany(mappedBy = "rentacar", fetch = FetchType.EAGER) 
	private Set<BranchLocations> branch_locations = new HashSet<BranchLocations>();
	/** @pdOid 4b5cfa10-6719-4561-919b-67a40409a869 */
	
	public RentACar() {
		super();
	}
	
	public RentACar(Long id, String name, String address, String description, int r) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = r;
	}
	
	public RentACar(RentACar r) {
		this(r.getId(),r.getName(),r.getAddress(),r.getDescription(),r.getRating());
	}
	
	
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
/*
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}*/
	
	
	
}
