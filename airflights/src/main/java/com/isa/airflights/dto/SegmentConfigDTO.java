package com.isa.airflights.dto;

import java.util.HashSet;
import java.util.Set;

import com.isa.airflights.model.Seat;

public class SegmentConfigDTO {

	private Long id;
	private Long airplane_id;
	private Integer segmentNum;
	private Set<Seat> seats = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAirplane_id() {
		return airplane_id;
	}
	public void setAirplane_id(Long airplane_id) {
		this.airplane_id = airplane_id;
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
	
	
	
	
}
