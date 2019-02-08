package com.isa.airflights.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "floor", nullable = false)
	private Integer floor;
	
	@Column(name = "number", nullable = false)
	private Integer number;
	
	@Column(name = "beds", nullable = false)
	private Integer beds;
	
	@Column(name = "rooms", nullable = false)
	private Integer rooms;
	
	@Column(name = "price", nullable = false)
	private Double price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;

    @Column(name = "balcony", nullable = false)
    private Boolean balcony;
    
    @Column(name = "promo", nullable = false)
    private Boolean promo;
    
    @Column(name = "discount", nullable = true)
    private Double discount;
    
	@Column(name = "rating_count", nullable = false)
	private Long ratingsCount = 0L;
	
	@Column(name = "rating_sum", nullable = false)
	private Long ratingsSum = 0L;
    
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

	public Integer getRooms() {
		return rooms;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room c = (Room) o;
        if(c.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
	
    public Room() {
		// Default 
	}
    
    public Room(Long id, Integer floor, Integer number, Integer beds, Integer rooms, Double price) {
    	this.id = id;
    	this.floor = floor;
    	this.number = number;
    	this.beds = beds;
    	this.rooms = rooms;
    	this.price = price;
    }
}
