package com.isa.airflights.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name = "airplane")
public class Airplane {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fullName", nullable = false)
	private String fullName;
	
	@ManyToOne
	@JoinColumn
	private Airline airline;
	
	@OneToOne(mappedBy = "airplane", cascade = CascadeType.ALL)
	private SegmentConfig segmentConfig;

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

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public SegmentConfig getSegmentConfig() {
		return segmentConfig;
	}

	public void setSegmentConfig(SegmentConfig segmentConfig) {
		this.segmentConfig = segmentConfig;
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
        Airplane airplane = (Airplane) obj;
        if (airplane.getId() == null || getId() == null) {
            return false;
        }
        return airplane.getId().equals(getId());
	}

	
	
	
	
	
	
}
