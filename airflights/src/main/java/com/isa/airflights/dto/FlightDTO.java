package com.isa.airflights.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.isa.airflights.model.enumtypes.AirlineClassType;
import com.isa.airflights.model.enumtypes.FlightType;

public class FlightDTO {

	private Long id;
	private Date departureDatetime;
	private Date arrivalDatetime;
	
	private FlightType flightType;
	
	
	private Long airlineId;
	
	
	private Long departureDestination;
	private Long arrivalDestination;
	
	
	
	//na osnovu id-jeva koji postoje u podrzanim combobox-ovima na frontu mozes da izvuces id-jeve letova
	private List<Long> flightLegsId = new ArrayList<>();
	private Map<AirlineClassType, Double> flightClassPricesMap = new TreeMap<>();
	
	private Integer travelTime;

	private Integer travelDistance;
	private Integer legCount;
	private Double flightDiscount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}
	public List<Long> getFlightLegsId() {
		return flightLegsId;
	}
	public void setFlightLegsId(List<Long> flightLegsId) {
		this.flightLegsId = flightLegsId;
	}
	public Map<AirlineClassType, Double> getFlightClassPricesMap() {
		return flightClassPricesMap;
	}
	public void setFlightClassPricesMap(Map<AirlineClassType, Double> flightClassPricesMap) {
		this.flightClassPricesMap = flightClassPricesMap;
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
	public Integer getLegCount() {
		return legCount;
	}
	public void setLegCount(Integer legCount) {
		this.legCount = legCount;
	}
	public Double getFlightDiscount() {
		return flightDiscount;
	}
	public void setFlightDiscount(Double flightDiscount) {
		this.flightDiscount = flightDiscount;
	}
	public FlightType getFlightType() {
		return flightType;
	}
	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}
	public Long getArrivalDestination() {
		return arrivalDestination;
	}
	public void setArrivalDestination(Long arrivalDestination) {
		this.arrivalDestination = arrivalDestination;
	}
	public Long getDepartureDestination() {
		return departureDestination;
	}
	public void setDepartureDestination(Long departureDestination) {
		this.departureDestination = departureDestination;
	}
	
	
	
}
