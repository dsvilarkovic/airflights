package com.isa.airflights.dto;



public class LuggagePriceDTO {
	private Long id;	
	/**
	 * Svaka cena odredjenog prtljaga pripada jednom cenovniku
	 */
	private Long luggagePriceList_id;	
	private Integer length;
	private Integer width;
	private Integer height;	
	private Integer weight;	
	private Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLuggagePriceList_id() {
		return luggagePriceList_id;
	}
	public void setLuggagePriceList_id(Long luggagePriceList_id) {
		this.luggagePriceList_id = luggagePriceList_id;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
