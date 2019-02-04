package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.jsonwebtoken.lang.Objects;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"flight_id", "seat_id"})}, name = "flightTicket")
public class FlightTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Kome je namenjena karta, kartu moze imati jedan korisnik, a korisnik moze imati vise karata
	 */
	@ManyToOne(optional = false)
	@JoinColumn
	private AbstractUser abstractUser;
	
	
	/**
	 * Svaka brza karta pripada jednoj kompaniji, a aviokompanija ima vise brzih karata rezervisanih
	 */
	@ManyToOne
	@JoinColumn
	private Airline airline;
	
	
	@Column(name = "isFastReservation")	
	private boolean isFastReservation = false;
	
	/**
	 * Svaka karta pripada  samo jednom letu, a let ima vise karata
	 */	
	@ManyToOne
	@JoinColumn
	private Flight flight;
	
	/**
	 * Svaka karta ima jednu i samo jednu klasu i cenu kojoj pripada na letu
	 */
	@ManyToOne
	@JoinColumn
	private FlightClassPrice flightClassPrice;
	
	

	/**
	 * Svaka karta moze biti za jedno i samo jedno sediste na letu, a svako sediste moze biti rezervisano za vise karata na razlicitim letovima <br>
	 * Dakle, isto sediste ne sme biti ponavljano (unique) za jedan let: unique(seat_id,flight_id)
	 * @return
	 */
	@ManyToOne
	@JoinColumn
	private Seat seat;
	
	/**
	 * Mora postojati i stanje karte, tj da li je otvorena ili rezervisana
	 */
	private boolean isReserved = false;
	
	
	/**
	 * Mnozitelj cene karte
	 */
	private Double priceReduction = 1.0;
	
	
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

	

	public boolean isFastReservation() {
		return isFastReservation;
	}


	public void setFastReservation(boolean isFastReservation) {
		this.isFastReservation = isFastReservation;
	}


	public FlightClassPrice getFlightClassPrice() {
		return flightClassPrice;
	}


	public void setFlightClassPrice(FlightClassPrice flightClassPrice) {
		this.flightClassPrice = flightClassPrice;
	}



	

	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}


	public boolean isReserved() {
		return isReserved;
	}


	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}


	public Double getPriceReduction() {
		return priceReduction;
	}


	public void setPriceReduction(Double priceReduction) {
		this.priceReduction = priceReduction;
	}


	public AbstractUser getAbstractUser() {
		return abstractUser;
	}


	public void setAbstractUser(AbstractUser abstractUser) {
		this.abstractUser = abstractUser;
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
