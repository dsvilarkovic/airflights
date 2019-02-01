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
	
	/**
	 * Svako sediste ima svoju klasu u avionu kojoj pripada
	 */
	@Column(name = "airline_class")
	private AirlineClassType airline_class;
	
	@Column(name = "seat_row")
	private Integer seat_row;
	
	@Column(name = "seat_column")
	private Integer seat_column;
	
	@Column(name = "segment_num")
	private Integer segment_num;
	
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "configuration")
	private SegmentConfig segmentConfig;
	
	
	@OneToMany(mappedBy = "seat")
	private Set<ReservedSeat> reservedSeats = new HashSet<>();
	
	
	


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	


	public Integer getSeat_row() {
		return seat_row;
	}


	public void setSeat_row(Integer seat_row) {
		this.seat_row = seat_row;
	}


	public Integer getSeat_column() {
		return seat_column;
	}


	public void setSeat_column(Integer seat_column) {
		this.seat_column = seat_column;
	}




	public Integer getSegment_num() {
		return segment_num;
	}


	public void setSegment_num(Integer segment_num) {
		this.segment_num = segment_num;
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


	
	public AirlineClassType getAirline_class() {
		return airline_class;
	}


	public void setAirline_class(AirlineClassType airline_class) {
		this.airline_class = airline_class;
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
