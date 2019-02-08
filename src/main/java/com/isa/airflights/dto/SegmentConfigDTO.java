package com.isa.airflights.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.isa.airflights.dto.SeatDTO;
import com.isa.airflights.model.SegmentConfig;

public class SegmentConfigDTO {

	private Long id;
	private Long airplaneId;
	private Integer segmentNum;
	private Set<SeatDTO> seats = new HashSet<>();
	
	public SegmentConfigDTO() {}
	public SegmentConfigDTO(SegmentConfig sg) {
		this.id = sg.getId();
		this.airplaneId = sg.getAirplane().getId();
		this.segmentNum = sg.getSegmentNum();
		this.seats = sg.getSeats().stream().map(seat -> new SeatDTO(seat)).collect(Collectors.toSet());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}
	public Integer getSegmentNum() {
		return segmentNum;
	}
	public void setSegmentNum(Integer segmentNum) {
		this.segmentNum = segmentNum;
	}
	public Set<SeatDTO> getSeats() {
		return seats;
	}
	public void setSeats(Set<SeatDTO> seatDTOs) {
		this.seats = seatDTOs;
	}

	
	
	
	
	
}