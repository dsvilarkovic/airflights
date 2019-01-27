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

import io.jsonwebtoken.lang.Objects;


@Entity
@Table(name = "flightClassPrice")
public class FlightClassPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	/**
	 * Svaka cena klase pripada jednom letu
	 */
	@ManyToOne
	@JoinColumn(name = "id")
	private Flight flight;
	
	
	/**
	 * Svaku cenu leta klase ima vise karata
	 */
	@OneToMany(mappedBy = "flightClassPrice")
	private Set<FlightTicket> flightTickets = new HashSet<>();
	
	
	
	/**
	 * cena
	 */
	@Column(name = "price", nullable = false)
	private Double price;
	
	
	/**
	 * klasa u letu kojoj pripada
	 */
	@Column(name = "class")
	private AirlineClassType airlineClassType;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Set<FlightTicket> getFlightTickets() {
		return flightTickets;
	}


	public void setFlightTickets(Set<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
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
        FlightClassPrice flightClassPrice = (FlightClassPrice) obj;
        if (flightClassPrice.getId() == null || getId() == null) {
            return false;
        }
        return flightClassPrice.getId().equals(getId());
	}
}
