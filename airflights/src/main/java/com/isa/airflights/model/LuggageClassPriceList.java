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
@Table(name = "luggageClassPriceList")
public class LuggageClassPriceList {
	
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
	
	
	@OneToMany(mappedBy = "luggageClassPriceList", cascade = CascadeType.ALL)
	private Set<LuggageClassPrice> luggageClassPrices = new HashSet<>();


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


	public Set<LuggageClassPrice> getLuggageClassPrices() {
		return luggageClassPrices;
	}


	public void setLuggageClassPrices(Set<LuggageClassPrice> luggageClassPrices) {
		this.luggageClassPrices = luggageClassPrices;
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
        LuggageClassPriceList luggageClassPriceList = (LuggageClassPriceList) obj;
        if (luggageClassPriceList.getId() == null || getId() == null) {
            return false;
        }
        return luggageClassPriceList.getId().equals(getId());
	}
	
	

}
