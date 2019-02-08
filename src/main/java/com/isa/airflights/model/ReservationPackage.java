package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Paket sa svim rezervacijama koje izvrsava nas korisnik
 * @author dusan
 *
 */
@Entity
public class ReservationPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tickets", nullable=false)
	private Long tickets;
	
	@Column
	private Boolean reservedRooms = false;
	

	/**
	 * Jedan korisnik i samo jedan prolazi kroz celu putanju
	 */
	
	
	/**
	 * Svaka rezervacija ima vise narucenih karata za neki let
	 */
	
	/**Nullable
	 * Svaka rezervacija ima vise kreveta/soba za hotel
	 */

	public Boolean getReservedRooms() {
		return reservedRooms;
	}

	public void setReservedRooms(Boolean reservedRooms) {
		this.reservedRooms = reservedRooms;
	}

	/**Nullable
	 * Svaka rezervacija ima vise kola za iznajmljivanje
	 */

	public Long getTickets() {
		return tickets;
	}

	public void setTickets(Long tickets) {
		this.tickets = tickets;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
