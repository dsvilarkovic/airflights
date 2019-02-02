package com.isa.airflights.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RentACarRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private double rating;
	

	@Column(name = "sum_for_rating") //deljenik
	private int sum;
	
	@Column(name = "last_rating") //delilac
	private double lastRating;
	
	
	@Column(nullable = false)
	private Long rentacar;
	


	
	public RentACarRating() {
		
	}
	
	public RentACarRating(Long id, double rating, Long rentacar,int sum, double last) {
		super();
		this.id = id;
		this.rating = rating;
		this.rentacar = rentacar;
		this.sum = sum;
		this.lastRating = last;
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

	public Long getRentacar() {
		return rentacar;
	}

	public void setRentacar(Long rentacar) {
		this.rentacar = rentacar;
	}


	
	
	
	
	
}
