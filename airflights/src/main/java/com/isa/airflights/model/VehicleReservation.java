package com.isa.airflights.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VehicleReservation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Vehicle vehicle;
	
	@ManyToOne
	private RentACar rentacar;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date reservationdate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date pickupdate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dropoffdate;
	
	@Column
	private String pickUpLocation;
	
	@Column
	private String dropOffLocation;
	
	@Column
	private double price;
	
	@ManyToOne
	private AbstractUser abstractUser;
	
	public VehicleReservation() {
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public RentACar getRentacar() {
		return rentacar;
	}

	public void setRentacar(RentACar rentacar) {
		this.rentacar = rentacar;
	}

	public Date getReservationdate() {
		return reservationdate;
	}

	public void setReservationdate(Date reservationdate) {
		this.reservationdate = reservationdate;
	}

	public Date getPickupdate() {
		return pickupdate;
	}

	public void setPickupdate(Date pickupdate) {
		this.pickupdate = pickupdate;
	}

	public Date getDropoffdate() {
		return dropoffdate;
	}

	public void setDropoffdate(Date dropoffdate) {
		this.dropoffdate = dropoffdate;
	}

	public String getPickUpLocation() {
		return pickUpLocation;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public String getDropOffLocation() {
		return dropOffLocation;
	}

	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AbstractUser getUser() {
		return abstractUser;
	}

	public void setUser(AbstractUser user) {
		this.abstractUser = user;
	}
	
	
}
