package com.isa.airflights.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Reservation {
	
	public Reservation() {
		// Default
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private RoomReservation roomRes;
    
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private VehicleReservation vehRes;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomReservation getRoomRes() {
		return roomRes;
	}

	public void setRoomRes(RoomReservation roomRes) {
		this.roomRes = roomRes;
	}

	public VehicleReservation getVehRes() {
		return vehRes;
	}

	public void setVehRes(VehicleReservation vehRes) {
		this.vehRes = vehRes;
	}
	

}
