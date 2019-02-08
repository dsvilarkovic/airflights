package com.isa.airflights.dto;

import com.isa.airflights.model.Seat;
import com.isa.airflights.model.enumtypes.AirlineClassType;

public class SeatDTO {
	private Long id;
	private AirlineClassType airlineClass;
	private Integer seatRow;
	private Integer seatColumn;
	private Integer segmentNum;
	
	private Long configuration;

	public SeatDTO() {}
	public SeatDTO(Seat s) {
		this.id = s.getId();
		this.airlineClass = s.getAirlineClass();
		this.seatRow = s.getSeatRow();
		this.seatColumn = s.getSeatColumn();
		this.segmentNum = s.getSegmentNum();
		this.configuration = s.getSegmentConfig().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


  
	public Integer getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(Integer seatRow) {
		this.seatRow = seatRow;
	}

	public Integer getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(Integer seatColumn) {
		this.seatColumn = seatColumn;
	}


	public Integer getSegmentNum() {
		return segmentNum;
	}

	public void setSegmentNum(Integer segmentNum) {
		this.segmentNum = segmentNum;
	}

	public Long getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Long configuration) {
		this.configuration = configuration;
	}

	public AirlineClassType getAirlineClass() {
		return airlineClass;
	}

	public void setAirlineClass(AirlineClassType airlineClass) {
		this.airlineClass = airlineClass;
	}
	
		
}