package com.isa.airflights.dto.rating;

import com.isa.airflights.model.rating.RentACarRating;

public class RentACarRatingDTO {

	
	private Long id;
		
	private double rating;

	 //deljenik
	private int sum;
	
	 //delilac
	private double lastRating;

	private Long rentacar;//rentacarId
	
	public RentACarRatingDTO() {
		
	}
	


	public RentACarRatingDTO(Long id, double rating, int sum, double lastRating, Long rentacar) {
		super();
		this.id = id;
		this.rating = rating;
		this.sum = sum;
		this.lastRating = lastRating;
		this.rentacar = rentacar;

	}
	
	public RentACarRatingDTO(RentACarRating r) {
		this(r.getId(), r.getRating(), r.getSum(), r.getLastRating(), r.getRentacar());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public double getLastRating() {
		return lastRating;
	}

	public void setLastRating(double lastRating) {
		this.lastRating = lastRating;
	}

	public Long getRentacar() {
		return rentacar;
	}

	public void setRentacar(Long rentacar) {
		this.rentacar = rentacar;
	}

	
	
	
	
}
