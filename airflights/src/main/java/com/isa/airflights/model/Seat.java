package com.isa.airflights.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name = "seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@Column(name = "seatRow")
	private Integer row;
	
	@Column(name = "seatColumn")
	private Integer column;
	
	@Column(name = "segmentNum")
	private Integer segmentNum;
	
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "configuration")
	private SegmentConfig segmentConfig;
	
	
	@OneToMany(mappedBy = "seat")
	private Set<ReservedSeat> reservedSeats = new HashSet<>();
	
	
	/**
	 * Svako sediste ima svoju klasu u avionu kojoj pripada
	 */
	@Column(name = "class")
	private AirlineClassType airlineClassType;


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Integer getRow() {
		return row;
	}


	public void setRow(Integer row) {
		this.row = row;
	}


	public Integer getColumn() {
		return column;
	}


	public void setColumn(Integer column) {
		this.column = column;
	}


	public Integer getSegmentNum() {
		return segmentNum;
	}


	public void setSegmentNum(Integer segmentNum) {
		this.segmentNum = segmentNum;
	}


	public SegmentConfig getSegmentConfig() {
		return segmentConfig;
	}


	public void setSegmentConfig(SegmentConfig segmentConfig) {
		this.segmentConfig = segmentConfig;
	}


	public Set<ReservedSeat> getReservedSeats() {
		return reservedSeats;
	}


	public void setReservedSeats(Set<ReservedSeat> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}


	public AirlineClassType getAirlineClassType() {
		return airlineClassType;
	}


	public void setAirlineClassType(AirlineClassType airlineClassType) {
		this.airlineClassType = airlineClassType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Seat seat = (Seat) obj;
        if (seat.getId() == null || getId() == null) {
            return false;
        }
        return seat.getId().equals(getId());
	}
	

}
