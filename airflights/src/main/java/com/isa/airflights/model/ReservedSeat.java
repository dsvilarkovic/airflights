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

import org.springframework.lang.NonNull;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name = "reservedSeat")
public class ReservedSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Svako rezervisano sediste pripada jednom i samo jednom sedistu
	 */
	
	@ManyToOne
	@JoinColumn
	private Seat seat;
	
	
	/**
	 * Svako rezervisano sediste pripada jednoj i samo jednoj karti
	 */
	@OneToOne(mappedBy = "reservedSeat")
	@NonNull
	private FlightTicket flightTicket;
	
	@Column(name = "discount")
	private Double discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public FlightTicket getFlightTicket() {
		return flightTicket;
	}

	public void setFlightTicket(FlightTicket flightTicket) {
		this.flightTicket = flightTicket;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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
        ReservedSeat reservedSeat = (ReservedSeat) obj;
        if (reservedSeat.getId() == null || getId() == null) {
            return false;
        }
        return reservedSeat.getId().equals(getId());
	}
	
	
	
}
