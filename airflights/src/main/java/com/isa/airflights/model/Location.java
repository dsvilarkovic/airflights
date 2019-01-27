package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name= "location")
public class Location {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "latitude")
	@NonNull
	private double latitude;
	
	@Column(name = "longitude")
	@NonNull
	private double longitude;
	
	@OneToOne(optional = true)
	private Airline airline;
	
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
	public AirportDestination getAirportDestination() {
		return airportDestination;
	}
	public void setAirportDestination(AirportDestination airportDestination) {
		this.airportDestination = airportDestination;
	}
	@OneToOne(optional = true)
	private AirportDestination airportDestination;
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
        Location location = (Location) obj;
        if (location.getId() == null || getId() == null) {
            return false;
        }
        return location.getId().equals(getId());
	}
}
