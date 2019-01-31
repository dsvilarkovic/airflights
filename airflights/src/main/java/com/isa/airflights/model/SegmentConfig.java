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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jsonwebtoken.lang.Objects;

@Entity
@Table(name = "segmentConfig")
public class SegmentConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	/**
	 * Svaka konfiguracija pripada jednom i samo jednom avionu
	 */
	@OneToOne
	@JoinColumn(unique = true)
	//@JoinColumn(name = "airplane_id", referencedColumnName = "id")
	private Airplane airplane;
	
	@Column(name = "segmentNum")
	private Integer segmentNum;	
	
	@OneToMany(mappedBy = "segmentConfig", cascade = CascadeType.ALL)
	private Set<Seat> seats = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Integer getSegmentNum() {
		return segmentNum;
	}

	public void setSegmentNum(Integer segmentNum) {
		this.segmentNum = segmentNum;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
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
        SegmentConfig segmentConfig = (SegmentConfig) obj;
        if (segmentConfig.getId() == null || getId() == null) {
            return false;
        }
        return segmentConfig.getId().equals(getId());
	}
}
