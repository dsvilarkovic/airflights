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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name="seq2", initialValue=4)
public class RentACar {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq2")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	/** @pdOid e0db3c3a-50d2-402c-b71c-973cbf62c53b */
	
	@Column(name = "address", nullable = true) 
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	
	@Column(name = "city", nullable = true) 
	private String city;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	
	//prosecna ocena servisa
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.EAGER) 
	private Set<BranchLocations> vehicles = new HashSet<BranchLocations>();
	

	@OneToMany(mappedBy = "rentacar", fetch = FetchType.EAGER) 
	private Set<BranchLocations> branch_locations = new HashSet<BranchLocations>();
	/** @pdOid 4b5cfa10-6719-4561-919b-67a40409a869 */
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "rentacar_id", referencedColumnName = "id")
	private Set<VehicleReservation> reservations = new HashSet<VehicleReservation>();
	
	public RentACar() {
		super();
	}
	
	public RentACar(Long id, String name, String address, String city, String description, int r) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = r;
		this.city = city;
	}
	
	public RentACar(RentACar r) {
		this(r.getId(),r.getName(),r.getAddress(),r.getCity(),r.getDescription(),r.getRating());
	}
	
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Set<BranchLocations> getBranch_locations() {
		return branch_locations;
	}

	public void setBranch_locations(Set<BranchLocations> branch_locations) {
		this.branch_locations = branch_locations;
	}
	
	
	
}
