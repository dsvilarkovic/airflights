package com.isa.airflights.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name = "flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "departureDatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDatetime;
	
	@Column(name = "arrivalDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDateTime;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Airline airline;
	
	
	@ManyToMany
	@JoinTable(name = "flight_flight_legs",
		joinColumns = { @JoinColumn(name = "id") },
		inverseJoinColumns = { @JoinColumn(name = "id") })
	private Set<AirportDestination> flightsLegs;
	
	/**
	 * Svaki let ima vise cena karata za razlicite klase
	 */
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<FlightClassPrice> flightClassPrices = new HashSet<>();
	
	@Column(name = "travelTime")	
	private Integer travelTime;
	
	
	@Column(name = "travelDistance")
	private Integer travelDistance;
	
	@Column(name = "legCount")
	private Integer legCount;
	
	
	@Column(name = "flightDiscount")
	private Double flightDiscount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDepartureDatetime() {
		return departureDatetime;
	}

	public void setDepartureDatetime(Date departureDatetime) {
		this.departureDatetime = departureDatetime;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Set<AirportDestination> getFlightsLegs() {
		return flightsLegs;
	}

	public void setFlightsLegs(Set<AirportDestination> flightsLegs) {
		this.flightsLegs = flightsLegs;
	}

	public Integer getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}

	public Integer getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(Integer travelDistance) {
		this.travelDistance = travelDistance;
	}

	public Integer getLegCount() {
		return legCount;
	}

	public void setLegCount(Integer legCount) {
		this.legCount = legCount;
	}

	public Double getFlightDiscount() {
		return flightDiscount;
	}

	public void setFlightDiscount(Double flightDiscount) {
		this.flightDiscount = flightDiscount;
	}

	public Set<FlightClassPrice> getFlightClassPrices() {
		return flightClassPrices;
	}

	public void setFlightClassPrices(Set<FlightClassPrice> flightClassPrices) {
		this.flightClassPrices = flightClassPrices;
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
        Flight flight = (Flight) obj;
        if (flight.getId() == null || getId() == null) {
            return false;
        }
        return flight.getId().equals(getId());
	}
	
	
	
}
