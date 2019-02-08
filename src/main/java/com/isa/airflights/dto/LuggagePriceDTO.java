package com.isa.airflights.dto;

import com.isa.airflights.model.LuggagePrice;

public class LuggagePriceDTO {
	private Long id;	
	/**
	 * Svaka cena odredjenog prtljaga pripada jednom cenovniku
	 */
	private Long luggagePriceListId;	
	private Integer length;
	private Integer width;
	private Integer height;	
	private Integer weight;	
	private Double price;
	
	
	public LuggagePriceDTO() {}
	
	public LuggagePriceDTO(LuggagePrice lp) {
		this.id = lp.getId();
		this.length = lp.getLength();
		this.width = lp.getWidth();
		this.height = lp.getHeight();
		this.weight = lp.getWeight();
		this.price = lp.getPrice();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLuggagePriceListId() {
		return luggagePriceListId;
	}
	public void setLuggagePriceListId(Long luggagePriceListId) {
		this.luggagePriceListId = luggagePriceListId;
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
