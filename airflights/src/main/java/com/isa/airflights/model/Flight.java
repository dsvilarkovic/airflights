package com.isa.airflights.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import com.isa.airflights.model.enumtypes.FlightType;

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
	
	@Column(name = "arrivalDatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDatetime;
	
	@Column(name = "flightType")
	private FlightType flightType;
	
	@ManyToOne
	@JoinColumn
	private Airline airline;
	
	
	@Column(name = "departureDestination")
	private Long departureDestination;
	
	@Column(name = "arrivalDestination")
	private Long arrivalDestination;
	
	@ManyToMany
	@JoinTable(name = "flight_flight_legs",
		joinColumns = { @JoinColumn(name = "flight_id") },
		inverseJoinColumns = { @JoinColumn(name = "airport_id") })
	private List<AirportDestination> flightsLegs = new ArrayList<AirportDestination>();
	
	/**
	 * Svaki let ima vise cena karata za razlicite klase
	 */
	@OneToMany(mappedBy = "flight", cascade = CascadeType.REFRESH)
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

	public Date getArrivalDatetime() {
		return arrivalDatetime;
	}

	public void setArrivalDatetime(Date arrivalDatetime) {
		this.arrivalDatetime = arrivalDatetime;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public List<AirportDestination> getFlightsLegs() {
		return flightsLegs;
	}

	public void setFlightsLegs(List<AirportDestination> flightsLegs) {
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
	
	

	public FlightType getFlightType() {
		return flightType;
	}

	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}

	

	public Long getDepartureDestination() {
		return departureDestination;
	}

	public void setDepartureDestination(Long departureDestination) {
		this.departureDestination = departureDestination;
	}

	public Long getArrivalDestination() {
		return arrivalDestination;
	}

	public void setArrivalDestination(Long arrivalDestination) {
		this.arrivalDestination = arrivalDestination;
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
