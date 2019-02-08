package com.isa.airflights.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.Room;

public class RoomDTO {
	
	private Long id;

	private Integer floor;

	private Integer number;

	private Integer beds;

	private Integer rooms;

	private Double price;

    private Long hotel_id;

    private Boolean balcony;

    private Boolean promo;

    private Double discount;

	private Long ratingsCount = 0L;

	private Long ratingsSum = 0L;
	
	
	public RoomDTO() {
		
	}
	

	public RoomDTO(Long id, Integer floor, Integer number, Integer beds, Integer rooms, Double price, Long hotel_id,
			Boolean balcony, Boolean promo, Double discount, Long ratingsCount, Long ratingsSum) {
		super();
		this.id = id;
		this.floor = floor;
		this.number = number;
		this.beds = beds;
		this.rooms = rooms;
		this.price = price;
		this.hotel_id = hotel_id;
		this.balcony = balcony;
		this.promo = promo;
		this.discount = discount;
		this.ratingsCount = ratingsCount;
		this.ratingsSum = ratingsSum;
	}
	
	public RoomDTO(Room r) {
		this(r.getId(),r.getFloor(),r.getNumber(),r.getBeds(),r.getRooms(),r.getPrice(),r.getHotel().getId()
				,r.getBalcony(),r.getPromo(),r.getDiscount(),r.getRatingsCount(),r.getRatingsSum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}

	public Boolean getPromo() {
		return promo;
	}

	public void setPromo(Boolean promo) {
		this.promo = promo;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Long getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Long ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Long getRatingsSum() {
		return ratingsSum;
	}

	public void setRatingsSum(Long ratingsSum) {
		this.ratingsSum = ratingsSum;
	}
	
	
	
	
	

}
