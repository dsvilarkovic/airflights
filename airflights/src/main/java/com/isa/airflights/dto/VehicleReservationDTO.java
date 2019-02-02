package com.isa.airflights.dto;

import com.isa.airflights.model.VehicleReservation;

public class VehicleReservationDTO {
    private Long id;
	
	
	private VehicleDTO vehicle;
	
	private Long user_id;
	
	private String reservationdate;
	
	
	private String pickupdate;
	
	
	private String dropoffdate;;
	
	
	private String pickuplocation;
	
	
	private String dropofflocation;
	
	
	private double price;
	
	private RentACarDTO rentacar;
	
	private boolean cancel;
	
	public VehicleReservationDTO() {
		
	}
	
	


	public VehicleReservationDTO(Long id, String reservationdate, String pickupdate,
			String dropoffdate, String pickuplocation, String dropofflocation, double price,boolean cancel) {
		super();
		this.id = id;
		this.reservationdate = reservationdate;
		this.pickupdate = pickupdate;
		this.dropoffdate = dropoffdate;
		this.pickuplocation = pickuplocation;
		this.dropofflocation = dropofflocation;
		this.price = price;
		this.cancel = cancel;
	}
	
	public VehicleReservationDTO(VehicleReservation v) {
		this(v.getId(),v.getReservationdate().toString(),v.getPickupdate().toString(),v.getDropoffdate().toString(),v.getPickUpLocation(),
				v.getDropOffLocation(),v.getPrice(),v.isCancel());
		this.vehicle = new VehicleDTO(v.getVehicle());
		this.user_id = v.getUser().getId();
		this.rentacar = new RentACarDTO(v.getRentacar());
	}




	



	public boolean isCancel() {
		return cancel;
	}




	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}




	public RentACarDTO getRentacar() {
		return rentacar;
	}




	public void setRentacar(RentACarDTO rentacar) {
		this.rentacar = rentacar;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public VehicleDTO getVehicle() {
		return vehicle;
	}


	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getReservationdate() {
		return reservationdate;
	}


	public void setReservationdate(String reservationdate) {
		this.reservationdate = reservationdate;
	}


	public String getPickupdate() {
		return pickupdate;
	}


	public void setPickupdate(String pickupdate) {
		this.pickupdate = pickupdate;
	}


	public String getDropoffdate() {
		return dropoffdate;
	}


	public void setDropoffdate(String dropoffdate) {
		this.dropoffdate = dropoffdate;
	}


	public String getPickuplocation() {
		return pickuplocation;
	}


	public void setPickuplocation(String pickuplocation) {
		this.pickuplocation = pickuplocation;
	}


	public String getDropofflocation() {
		return dropofflocation;
	}


	public void setDropofflocation(String dropofflocation) {
		this.dropofflocation = dropofflocation;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
