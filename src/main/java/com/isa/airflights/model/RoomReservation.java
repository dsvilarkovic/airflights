package com.isa.airflights.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class RoomReservation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column
	private Double price;
	
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ReservationPackage reservation;
    
    @Column
    private Boolean rated = false;
    
	@Column(name="active", nullable=true)
	private Boolean active = true;
	
	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date resDate;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AbstractUser abstractUser;

	
	/*
	 * @djuka ubacio
	 * */
	@Column
	private Boolean rateRoom;
	
	@Column
	private Boolean rateHotel;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date reservationDate;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hotel hotel;
	
	
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isRateRoom() {
		return rateRoom;
	}

	public void setRateRoom(boolean rateRoom) {
		this.rateRoom = rateRoom;
	}

	public Boolean isRateHotel() {
		return rateHotel;
	}

	public void setRateHotel(Boolean rateHotel) {
		this.rateHotel = rateHotel;
	}

	public AbstractUser getAbstractUser() {
		return abstractUser;
	}

	public void setAbstractUser(AbstractUser abstractUser) {
		this.abstractUser = abstractUser;
	}

	public Boolean getRated() {
		return rated;
	}

	public void setRated(Boolean rated) {
		this.rated = rated;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ReservationPackage getReservation() {
		return reservation;
	}

	public void setReservation(ReservationPackage reservation) {
		this.reservation = reservation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
