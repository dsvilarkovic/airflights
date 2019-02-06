package com.isa.airflights.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.airflights.repository.AirlineRepository;
import com.isa.airflights.repository.HotelRepository;
import com.isa.airflights.repository.RentACarRepository;
import com.isa.airflights.service.HotelService;

public class AdminDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String kind;
	private String com;
	
	public AdminDTO(AbstractUser u) {
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.id = u.getId();
		this.email = u.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}
	
	
}
