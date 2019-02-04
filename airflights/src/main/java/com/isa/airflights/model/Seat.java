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

import com.isa.airflights.model.enumtypes.AirlineClassType;

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
	private Integer seatRow;
	
	@Column(name = "seat_column")
	private Integer seatColumn;
	
	@Column(name = "segment_num")
	private Integer segmentNum;
	
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "configuration")
	private SegmentConfig segmentConfig;
	
	
	/**
	 * Sediste postoji na vise karata, tj vise razlicitih flightTicket
	 * 
	 */
	@OneToMany(mappedBy = "seat")
	private Set<FlightTicket> flightTickets = new HashSet<>();
	


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	


	public Integer getSeatRow() {
		return seatRow;
	}


	public void setSeatRow(Integer seatRow) {
		this.seatRow = seatRow;
	}


	public Integer getSeatColumn() {
		return seatColumn;
	}


	public void setSeatColumn(Integer seatColumn) {
		this.seatColumn = seatColumn;
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
