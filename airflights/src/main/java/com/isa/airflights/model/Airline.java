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
	@OneToMany(mappedBy= "airline", cascade = CascadeType.REFRESH)
	private Set<Flight> flights = new HashSet<>();
	
	/**
	 * Konfiguraciju segmenata i mesta u avionima
	 */
	@OneToMany(mappedBy= "airline", cascade = CascadeType.REFRESH)
	private Set<Airplane> airplanes = new HashSet<>();
	
	/**
	 * Spisak karata sa popustima za brzu rezervaciju 
	 */
	@OneToMany(mappedBy = "airline", cascade = CascadeType.REFRESH)
	private Set<FlightTicket> fastDiscountedTickets = new HashSet<>();
	
	
	/**
	 * Cenovnik i informacije o prtljagu 
	 */
	@OneToOne(mappedBy = "airline", cascade = CascadeType.REFRESH)
	private LuggagePriceList luggagePriceList;


	@OneToMany(mappedBy = "airline")
	private Set<AbstractUser> admins;
	
	@Column(name="active", nullable=true)
	Boolean active = true;
	
	@Column(name="address", nullable=true)
	String address;
	
	@Column(name="city", nullable=true)
	String city;
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


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


	public LuggagePriceList getLuggagePriceList() {
		return luggagePriceList;
	}


	public void setLuggagePriceList(LuggagePriceList luggagePriceList) {
		this.luggagePriceList = luggagePriceList;
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
