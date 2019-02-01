package com.isa.airflights.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import io.jsonwebtoken.lang.Objects;


@Entity
@Table(name = "airline")
public class Airline {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Naziv aviokompanije
	 * 
	 * */
	@Column(name = "fullName", nullable = false)
	private String fullName;
	
	/**
	 * Adresu (dodatno prikaz lokacije korišćenjem Google mapa)
	 */
	@OneToOne
	@JoinColumn(unique = true)
	@NonNull
	private Location location;
	
	/**
	 * Promotivni opis
	 */
	@Column(name = "promoInfo", nullable = false)
	private String promoInfo;
	
	/**
	 * Destinacije na kojima posluje 
	 */
	@ManyToMany
	@JoinTable(name = "airlineDestinations",
	joinColumns = @JoinColumn(name = "airline_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"))
	private Set<AirportDestination> destinations = new HashSet<>();
	
	/**
	 * Letove
	 */
	@OneToMany(mappedBy= "airline", cascade = CascadeType.ALL)
	private Set<Flight> flights = new HashSet<>();
	
	/**
	 * Konfiguraciju segmenata i mesta u avionima
	 */
	@OneToMany(mappedBy= "airline", cascade = CascadeType.ALL)
	private Set<Airplane> airplanes = new HashSet<>();
	
	/**
	 * Spisak karata sa popustima za brzu rezervaciju 
	 */
	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	private Set<FlightTicket> fastDiscountedTickets = new HashSet<>();
	
	
	/**
	 * Cenovnik i informacije o prtljagu 
	 */
	@OneToOne(mappedBy = "airline", cascade = CascadeType.ALL)
	private LuggageClassPriceList luggageClassPriceList;


	@OneToMany(mappedBy = "airline")
	private Set<AbstractUser> admins;
	
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


	public String getPromoInfo() {
		return promoInfo;
	}


	public void setPromoInfo(String promoInfo) {
		this.promoInfo = promoInfo;
	}


	public Set<AirportDestination> getDestinations() {
		return destinations;
	}


	public void setDestinations(Set<AirportDestination> destinations) {
		this.destinations = destinations;
	}


	public Set<Flight> getFlights() {
		return flights;
	}


	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}


	public Set<Airplane> getAirplanes() {
		return airplanes;
	}


	public void setAirplanes(Set<Airplane> airplanes) {
		this.airplanes = airplanes;
	}


	public Set<FlightTicket> getFastDiscountedTickets() {
		return fastDiscountedTickets;
	}


	public void setFastDiscountedTickets(Set<FlightTicket> fastDiscountedTickets) {
		this.fastDiscountedTickets = fastDiscountedTickets;
	}


	public LuggageClassPriceList getLuggageClassPriceList() {
		return luggageClassPriceList;
	}


	public void setLuggageClassPriceList(LuggageClassPriceList luggageClassPriceList) {
		this.luggageClassPriceList = luggageClassPriceList;
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
        Airline airline = (Airline) obj;
        if (airline.getId() == null || getId() == null) {
            return false;
        }
        return airline.getId().equals(getId());
	}


	

}
