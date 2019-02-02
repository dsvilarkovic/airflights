package com.isa.airflights.dto;

import com.isa.airflights.model.enumtypes.AirlineClassType;

public class SeatDTO {
	private Long id;
	private AirlineClassType airline_class;
	private Integer seat_row;
	private Integer seat_column;
	private Integer segment_num;
	
	private Long configuration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


  
	public Integer getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(Integer seat_row) {
		this.seat_row = seat_row;
	}

	public Integer getSeat_column() {
		return seat_column;
	}

	public void setSeat_column(Integer seat_column) {
		this.seat_column = seat_column;
	}


	public Integer getSegment_num() {
		return segment_num;
	}

	public void setSegment_num(Integer segment_num) {
		this.segment_num = segment_num;
	}

	public Long getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Long configuration) {
		this.configuration = configuration;
	}

	public AirlineClassType getAirline_class() {
		return airline_class;
	}

	public void setAirline_class(AirlineClassType airline_class) {
		this.airline_class = airline_class;
	}
	
		
}
