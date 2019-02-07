package com.isa.airflights.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class RoomResExtras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private RoomReservation room;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private HotelExtras extras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomReservation getRoom() {
		return room;
	}

	public void setRoom(RoomReservation room) {
		this.room = room;
	}

	public HotelExtras getExtras() {
		return extras;
	}

	public void setExtras(HotelExtras extras) {
		this.extras = extras;
	}

}
