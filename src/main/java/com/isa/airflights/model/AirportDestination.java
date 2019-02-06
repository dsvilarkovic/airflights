package com.isa.airflights.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jsonwebtoken.lang.Objects;

/**
 * Lokacija aerodroma podrzanih ,sa punim imenom i pozicijama
 * @author Dusan
 *
 */
@Entity
@Table(name = "airport")
public class AirportDestination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fullName", nullable = false)
	private String fullName;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Location location;
	
	@ManyToMany(mappedBy = "destinations")
	private Set<Airline> airlines = new HashSet<>();
	
	@ManyToMany(mappedBy = "flightsLegs")
	private Set<Flight> flights= new HashSet<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Set<Airline> getAirlines() {
		return airlines;
	}
	public void setAirlines(Set<Airline> airlines) {
		this.airlines = airlines;
	}
	
	public Set<Flight> getFlights() {
		return flights;
	}
	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
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
        AirportDestination airportDestination= (AirportDestination) obj;
        if (airportDestination.getId() == null || getId() == null) {
            return false;
        }
        return airportDestination.getId().equals(getId());
	}
	
	
	
}
