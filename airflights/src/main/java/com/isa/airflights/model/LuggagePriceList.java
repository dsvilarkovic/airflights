package com.isa.airflights.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import io.jsonwebtoken.lang.Objects;

/**
 * Cenovnik za odredjene avione
 * Svaki cenovnik ima za svaku klasu i svaki prtljag neku cenu
 * @author Dusan
 *
 */
@Entity
@Table(name = "luggagePriceList")
public class LuggagePriceList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	/**
	 * Svaki cenovnik pripada jednoj i samo jednoj aviokompaniji
	 */
	@OneToOne
	@NonNull
	@JoinColumn(unique = true)
	private Airline airline;
	
	
	@OneToMany(mappedBy = "luggagePriceList", cascade = CascadeType.ALL)
	private Set<LuggagePrice> luggagePrices = new HashSet<>();


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


	public Set<LuggagePrice> getLuggagePrices() {
		return luggagePrices;
	}


	public void setLuggagePrices(Set<LuggagePrice> luggagePrices) {
		this.luggagePrices = luggagePrices;
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
        LuggagePriceList luggagePriceList = (LuggagePriceList) obj;
        if (luggagePriceList.getId() == null || getId() == null) {
            return false;
        }
        return luggagePriceList.getId().equals(getId());
	}
	
	

}
