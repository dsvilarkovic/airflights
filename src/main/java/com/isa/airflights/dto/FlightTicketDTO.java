package com.isa.airflights.dto;

import java.util.Date;

import com.isa.airflights.model.enumtypes.AirlineClassType;

public class FlightTicketDTO {
	private Long id;
	private AbstractUserDTO abstractUserDTO;
	private Long flightId;
	
	private AirportDestinationDTO departureDestination;
	private AirportDestinationDTO arrivalDestination;
	private Date departureDatetime;
	private Date arrivalDatetime;
	private Integer travelTime;
	private Integer travelDistance;
	
	private SeatDTO seatDTO;
	
	private Boolean isFastReservation = false;
	private Boolean isAccepted = false;
	
	/**Ocenjivanje leta, ako su 0, onda nije ocenjeno, 1-5 su ocene*/
	private Integer flightGrade = 0;
	private Integer airlineGrade = 0;
	
	/**Prava cena*/
	private Double flightClassPrice;
	private AirlineClassType airlineClassType;
	private Double priceReduction = 1.0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public AbstractUserDTO getAbstractUserDTO() {
		return abstractUserDTO;
	}
	public void setAbstractUserDTO(AbstractUserDTO abstractUserDTO) {
		this.abstractUserDTO = abstractUserDTO;
	}
	public AirportDestinationDTO getDepartureDestination() {
		return departureDestination;
	}
	public void setDepartureDestination(AirportDestinationDTO departureDestination) {
		this.departureDestination = departureDestination;
	}
	public AirportDestinationDTO getArrivalDestination() {
		return arrivalDestination;
	}
	public void setArrivalDestination(AirportDestinationDTO arrivalDestination) {
		this.arrivalDestination = arrivalDestination;
	}
	public Date getDepartureDatetime() {
		return departureDatetime;
	}
	public void setDepartureDatetime(Date departureDatetime) {
		this.departureDatetime = departureDatetime;
	}
	public Date getArrivalDatetime() {
		return arrivalDatetime;
	}
	public void setArrivalDatetime(Date arrivalDatetime) {
		this.arrivalDatetime = arrivalDatetime;
	}
	public Integer getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}
	public Integer getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(Integer travelDistance) {
		this.travelDistance = travelDistance;
	}
	public Boolean getIsFastReservation() {
		return isFastReservation;
	}
	public void setIsFastReservation(Boolean isFastReservation) {
		this.isFastReservation = isFastReservation;
	}
	public Double getFlightClassPrice() {
		return flightClassPrice;
	}
	public void setFlightClassPrice(Double flightClassPrice) {
		this.flightClassPrice = flightClassPrice;
	}
	public Double getPriceReduction() {
		return priceReduction;
	}
	public void setPriceReduction(Double priceReduction) {
		this.priceReduction = priceReduction;
	}
	public SeatDTO getSeatDTO() {
		return seatDTO;
	}
	public void setSeatDTO(SeatDTO seatDTO) {
		this.seatDTO = seatDTO;
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public AirlineClassType getAirlineClassType() {
		return airlineClassType;
	}
	public void setAirlineClassType(AirlineClassType airlineClassType) {
		this.airlineClassType = airlineClassType;
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
	
	
	
	
	
	
}