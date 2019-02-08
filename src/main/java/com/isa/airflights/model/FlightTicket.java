package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.jsonwebtoken.lang.Objects;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"flight_id", "seat_id"})}, name = "flightTicket")
public class FlightTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Kome je namenjena karta, kartu moze imati jedan korisnik, a korisnik moze imati vise karata
	 */
	@ManyToOne(optional = false)
	@JoinColumn
	private AbstractUser abstractUser = new AbstractUser();
	
	
	
	
	
	/**
	 * Svaka karta pripada  samo jednom letu, a let ima vise karata
	 */	
	@ManyToOne
	@JoinColumn
	private Flight flight;
	
	/**
	 * Svaka karta ima jednu i samo jednu klasu i cenu kojoj pripada na letu
	 */
	@ManyToOne
	@JoinColumn
	private FlightClassPrice flightClassPrice;
	
	

	/**
	 * Svaka karta moze biti za jedno i samo jedno sediste na letu, a svako sediste moze biti rezervisano za vise karata na razlicitim letovima <br>
	 * Dakle, isto sediste ne sme biti ponavljano (unique) za jedan let: unique(seat_id,flight_id)
	 * @return
	 */
	@ManyToOne
	@JoinColumn
	private Seat seat;
	
	/**Ocene rezervacija, ako je 0.0 nije ocenjeno */
	@Column(name = "flightGrade")
	private Integer flightGrade = 0;
	@Column(name = "airlineGrade")
	private Integer airlineGrade = 0;
	
	/**Stanja  rezervacije*/	
	@Column(name = "isFastReservation", nullable = false)	
	private Boolean isFastReservation = false;
	
	@Column(name = "isAccepted", nullable = false)
	private Boolean isAccepted = false;
	/**
	 * Mnozitelj cene karte
	 */
	@Column(name = "priceReduction", nullable = false)
	private Double priceReduction = 1.0;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	

	public boolean isFastReservation() {
		return isFastReservation;
	}


	public void setFastReservation(boolean isFastReservation) {
		this.isFastReservation = isFastReservation;
	}


	public FlightClassPrice getFlightClassPrice() {
		return flightClassPrice;
	}


	public void setFlightClassPrice(FlightClassPrice flightClassPrice) {
		this.flightClassPrice = flightClassPrice;
	}



	

	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}


	public Double getPriceReduction() {
		return priceReduction;
	}


	public void setPriceReduction(Double priceReduction) {
		this.priceReduction = priceReduction;
	}


	public AbstractUser getAbstractUser() {
		return abstractUser;
	}


	public void setAbstractUser(AbstractUser abstractUser) {
		this.abstractUser = abstractUser;
	}




	public Boolean getIsFastReservation() {
		return isFastReservation;
	}


	public void setIsFastReservation(Boolean isFastReservation) {
		this.isFastReservation = isFastReservation;
	}


	public Boolean getIsAccepted() {
		return isAccepted;
	}


	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}




	public Integer getFlightGrade() {
		return flightGrade;
	}


	public void setFlightGrade(Integer flightGrade) {
		this.flightGrade = flightGrade;
	}


	public Integer getAirlineGrade() {
		return airlineGrade;
	}


	public void setAirlineGrade(Integer airlineGrade) {
		this.airlineGrade = airlineGrade;
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
        FlightTicket flightTicket = (FlightTicket) obj;
        if (flightTicket.getId() == null || getId() == null) {
            return false;
        }
        return flightTicket.getId().equals(getId());
	}

}