package com.isa.airflights.model;

import java.util.List;

public class SearchObject {
	
	public SearchObject() {
		// Default
	}
	
	private Integer startD;

	private Integer endD;
	
	private Integer startM;

	private Integer endM;
	
	private Integer startY;
	
	private Integer endY;
	
	private String name;
	
	private String location;
	
	private Integer persons;
	
	private Double pf;
	
	private Double pt;
	
	private List<Condition> conditions;

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public Integer getStartD() {
		return startD;
	}

	public void setStartD(Integer startD) {
		this.startD = startD;
	}

	public Integer getEndD() {
		return endD;
	}

	public void setEndD(Integer endD) {
		this.endD = endD;
	}

	public Integer getStartM() {
		return startM;
	}

	public void setStartM(Integer startM) {
		this.startM = startM;
	}

	public Integer getEndM() {
		return endM;
	}

	public void setEndM(Integer endM) {
		this.endM = endM;
	}

	public Integer getStartY() {
		return startY;
	}

	public void setStartY(Integer startY) {
		this.startY = startY;
	}

	public Integer getEndY() {
		return endY;
	}

	public void setEndY(Integer endY) {
		this.endY = endY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getPt() {
		return pt;
	}

	public void setPt(Double pt) {
		this.pt = pt;
	}
	
	

}
