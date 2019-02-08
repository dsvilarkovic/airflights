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
	@Column(name="b", nullable=false)
	private Integer b;
	
	// Bonus bodova za rezervaciju leta i jos necega
	@Column(name="b2", nullable=false)
	private Integer bb;
	
	// Bonus bodova za rezervaciju svega
	@Column(name="b3", nullable=false)
	private Integer bbb;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public Integer getBb() {
		return bb;
	}

	public void setBb(Integer bb) {
		this.bb = bb;
	}

	public Integer getBbb() {
		return bbb;
	}

	public void setB3(Integer bbb) {
		this.bbb = bbb;
	}

	public Misc(Long id, Integer b, Integer bb, Integer bbb) {
		this.id = id;
		this.b = b;
		this.bb = bb;
		this.bbb = bbb;
	}


}
