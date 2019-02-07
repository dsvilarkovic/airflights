package com.isa.airflights.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.ReservationPackage;
import com.isa.airflights.model.Room;
import com.isa.airflights.model.RoomReservation;

public class RoomResMockDTO {
	

	private Long id;
	
	private String reservationDate;
	
	private String startDate;
	
	private String endDate;
	
	private Double price;
	
	private RoomDTO room;
    
	Boolean active = true;
	
	private HotelDTO hotel;
	
	private Long user_id;
	
	private boolean rateRoom;

	private boolean rateHotel;
	
	
	
	public RoomResMockDTO() {
		
	}
	
	

	public RoomResMockDTO(Long id, String startDate, String endDate, Double price, Boolean active,
			Long user_id, boolean rateH, boolean rateR,String resd) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.active = active;
		this.user_id = user_id;
		this.rateHotel = rateH;
		this.rateRoom = rateR;
		this.reservationDate = resd;
	}
	
	public RoomResMockDTO(RoomReservation r) {
		this(r.getId(),r.getStartDate().toString(),r.getEndDate().toString(),r.getPrice()
				,r.getActive(),r.getAbstractUser().getId(),
				r.isRateHotel(),r.isRateRoom(),r.getReservationDate().toString());
		
		//posebno za hotel i room
		this.room = new RoomDTO(r.getRoom());
		this.hotel = new HotelDTO(r.getHotel());
	}

	
	
	
	public RoomDTO getRoom() {
		return room;
	}



	public void setRoom(RoomDTO room) {
		this.room = room;
	}



	public HotelDTO getHotel() {
		return hotel;
	}



	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}



	public String getReservationDate() {
		return reservationDate;
	}



	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}



	public boolean isRateRoom() {
		return rateRoom;
	}



	public void setRateRoom(boolean rateRoom) {
		this.rateRoom = rateRoom;
	}



	public boolean isRateHotel() {
		return rateHotel;
	}



	public void setRateHotel(boolean rateHotel) {
		this.rateHotel = rateHotel;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}



	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	

}
