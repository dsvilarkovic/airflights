package com.isa.airflights.dto;

import java.util.List;

import com.isa.airflights.model.SearchObject;

public class RoomResDTO {
	
	//private Long id;
	private SearchObject obj;
	private List<Long> extras;
	private Long room_id;
	
	public SearchObject getObj() {
		return obj;
	}
	public void setObj(SearchObject obj) {
		this.obj = obj;
	}
	public List<Long> getExtras() {
		return extras;
	}
	public void setExtras(List<Long> extras) {
		this.extras = extras;
	}
	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}
	
	

}
