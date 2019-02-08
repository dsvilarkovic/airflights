package com.isa.airflights.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.isa.airflights.model.enumtypes.FlightType;

import io.jsonwebtoken.lang.Objects;

@Entity
@SequenceGenerator(name="flight_seq", initialValue=10)
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="flight_seq")
	private Long id;

	@Column(name = "departureDatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDatetime;
	
	@Column(name = "arrivalDatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDatetime;
	
	@Column(name = "flightType")
	@Enumerated(EnumType.ORDINAL)
	private FlightType flightType;
	
	@ManyToOne(optional = false)
	@JoinColumn
	private Airline airline;
	
	/*svaki let je podrzan jednim avionom, a jedan avion moze podrzati vise letova*/
	@ManyToOne(optional = true)
	@JoinColumn
	private Airplane airplane;
	
	
	@Column(name = "departureDestination")
	private Long departureDestination;
	
	@Column(name = "arrivalDestination")
	private Long arrivalDestination;
	
	/**
	 * Broj destinacija u letu
	 */
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "flight_flight_legs",
		joinColumns = { @JoinColumn(name = "flight_id") },
		inverseJoinColumns = { @JoinColumn(name = "airport_id") })
	private List<AirportDestination> flightsLegs = new ArrayList<AirportDestination>();
	
	/**
	 * Svaki let ima vise cena karata za razlicite klase
	 */
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<FlightClassPrice> flightClassPrices = new HashSet<>();
	
	/**
	 * Svaki let ima vise karata za let
	 */
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<FlightTicket> flightTickets;
	
	
	
	
	@Column(name = "travelTime")	
	private Integer travelTime;
	
	
	@Column(name = "travelDistance")
	private Integer travelDistance;
	
	@Column(name = "legCount")
	private Integer legCount;
	
	
	@Column(name = "flightDiscount")
	private Double flightDiscount;
	
	
	/**Ocene korisnika*/
	@Column(name = "gradeSum")
	private Double gradeSum = 0.0;
	@Column(name = "gradeCount")
	private Integer gradeCount = 0;

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
	

	public Set<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(Set<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}
	
	

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Double getGradeSum() {
		return gradeSum;
	}

	public void setGradeSum(Double gradeSum) {
		this.gradeSum = gradeSum;
	}

	

	public Integer getGradeCount() {
		return gradeCount;
	}

	public void setGradeCount(Integer gradeCount) {
		this.gradeCount = gradeCount;
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

//	@Override
//	public String toString() {
//		return "Flight [id=" + id + ", departureDatetime=" + departureDatetime + ", arrivalDatetime=" + arrivalDatetime
//				+ ", flightType=" + flightType + ", airline=" + airline + ", airplane=" + airplane
//				+ ", departureDestination=" + departureDestination + ", arrivalDestination=" + arrivalDestination
//				+ ", flightsLegs=" + flightsLegs + ", flightClassPrices=" + flightClassPrices + ", flightTickets="
//				+ flightTickets + ", travelTime=" + travelTime + ", travelDistance=" + travelDistance + ", legCount="
//				+ legCount + ", flightDiscount=" + flightDiscount + ", gradeSum=" + gradeSum + ", gradeCount="
//				+ gradeCount + "]";
//	}
//	
	
	
}