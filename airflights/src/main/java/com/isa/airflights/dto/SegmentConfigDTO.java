package com.isa.airflights.dto;

import java.util.HashSet;
import java.util.Set;

import com.isa.airflights.dto.SeatDTO;

public class SegmentConfigDTO {

	private Long id;
	private Long airplaneId;
	private Integer segmentNum;
	private Set<SeatDTO> seatDTOs = new HashSet<>();
	
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
	public Set<SeatDTO> getSeatDTOs() {
		return seatDTOs;
	}
	public void setSeatDTOs(Set<SeatDTO> seatDTOs) {
		this.seatDTOs = seatDTOs;
	}

	
	
	
	
	
}
