package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jsonwebtoken.lang.Objects;


@Entity
@Table(name = "flightTicket")
public class FlightTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Svaka karta pripada jednoj kompaniji
	 */
	@ManyToOne
	@JoinColumn
	private Airline airline;
	
	
	@Column(name = "isFast")	
	private boolean isFast = false;
	
	/**
	 * Svaka karta ima jednu i samo jednu klasu i cenu kojoj pripada na letu
	 */
	@ManyToOne
	@JoinColumn
	private FlightClassPrice flightClassPrice;
	
	/**
	 * Svaka karta pripada jednom i samo jednom sedistu
	 */
	@OneToOne
	@JoinColumn(unique = true)
	private ReservedSeat reservedSeat;
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Airline getAirline() {
		return airline;
	}


	public void setAirline(Airline airline) {
		this.airline = airline;
	}


	public boolean isFast() {
		return isFast;
	}


	public void setFast(boolean isFast) {
		this.isFast = isFast;
	}


	public FlightClassPrice getFlightClassPrice() {
		return flightClassPrice;
	}


	public void setFlightClassPrice(FlightClassPrice flightClassPrice) {
		this.flightClassPrice = flightClassPrice;
	}


	public ReservedSeat getReservedSeat() {
		return reservedSeat;
	}


	public void setReservedSeat(ReservedSeat reservedSeat) {
		this.reservedSeat = reservedSeat;
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
        FlightTicket flightTicket = (FlightTicket) obj;
        if (flightTicket.getId() == null || getId() == null) {
            return false;
        }
        return flightTicket.getId().equals(getId());
	}

}
