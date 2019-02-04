package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Misc {
	
	public Misc() {
		// Default
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Bonus bodova za rezervaciju leta
	@Column
	private Integer fr;
	
	// Bonus bodova za rezervaciju hotela
	@Column
	private Integer hr;
	
	// Bonus bodova za rezervaciju vozila
	@Column
	private Integer vr;
	
	// Bonus bodova za rezervaciju leta i hotela
	@Column
	private Integer fhr;
	
	// Bonus bodova za rezervaciju leta i vozila
	@Column
	private Integer fvr;
	
	// Bonus bodova za rezervaciju vozila i hotela
	@Column
	private Integer hvr;
	
	// Bonus bodova za rezervaciju svega
	@Column
	private Integer allr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}

	public Integer getHr() {
		return hr;
	}

	public void setHr(Integer hr) {
		this.hr = hr;
	}

	public Integer getVr() {
		return vr;
	}

	public void setVr(Integer vr) {
		this.vr = vr;
	}

	public Integer getFhr() {
		return fhr;
	}

	public void setFhr(Integer fhr) {
		this.fhr = fhr;
	}

	public Integer getFvr() {
		return fvr;
	}

	public void setFvr(Integer fvr) {
		this.fvr = fvr;
	}

	public Integer getHvr() {
		return hvr;
	}

	public void setHvr(Integer hvr) {
		this.hvr = hvr;
	}

	public Integer getAllr() {
		return allr;
	}

	public void setAllr(Integer allr) {
		this.allr = allr;
	}

}
