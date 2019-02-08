package com.isa.airflights.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;



@Entity
@SequenceGenerator(name="seq3", initialValue=7)
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq3")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "brand", nullable = false)
	private String brand;
	
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "year", nullable = false)
	private int year;
	
	@Column(name = "seats", nullable = false)
	private int seats;
	
	@Column(name = "type", nullable = false)
	private VehicleType type;
	
	@Column(name = "rating", nullable = false)
	private double rating;
	
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "reserved", nullable = true)
	private boolean reserved;
	
	//vrednosti ce biti od (0-1], ukoliko je vrednost polja null, vozilo nije na popustu
	@Column(name = "discount", nullable = true)
	private double discount;
	
	@Column(name = "rating_count", nullable = false)
	private double ratingsCount;
	
	@Column(name = "rating_sum", nullable = false)
	private double ratingsSum;

	@ManyToOne 
	private RentACar rentACar;
	
	@ManyToOne 
	private BranchLocations branch_locations;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<VehicleReservation> reservations = new HashSet<VehicleReservation>();
	
	@Version
	@Column
	private Long version;
	
	public Vehicle() {
		//super();
		// TODO Auto-generated constructor stub
	}



	public Vehicle(Long id, String name, String brand, String model, int year, int seats, 
			VehicleType type, double rating, double price, boolean r,double discount,double count,double sum) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.seats = seats;
		this.type = type;
		this.rating = rating;
		this.price = price;
		this.reserved = r;
		this.discount = discount;
		this.ratingsCount = count;
		this.ratingsSum = sum;

	}
	
	public Vehicle(Vehicle v) {
		this(v.getId(),v.getName(),v.getBrand(),v.getModel(),v.getYear(),v.getSeats(),v.getType(),
				v.getRating(),v.getPrice(),v.getReserved(),v.getDiscount(),v.getRatingsCount(),v.getRatingsSum());
	}



	
	



	


	public Long getVersion() {
		return version;
	}



	public void setVersion(Long version) {
		this.version = version;
	}



	public double getRatingsCount() {
		return ratingsCount;
	}



	public void setRatingsCount(double ratingsCount) {
		this.ratingsCount = ratingsCount;
	}



	public double getRatingsSum() {
		return ratingsSum;
	}



	public void setRatingsSum(double ratingsSum) {
		this.ratingsSum = ratingsSum;
	}



	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}



	public boolean getReserved() {
		return reserved;
	}



	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}



	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public int getSeats() {
		return seats;
	}



	public void setSeats(int seats) {
		this.seats = seats;
	}



	public VehicleType getType() {
		return type;
	}


	public void setType(VehicleType type) {
		this.type = type;
	}
	
	public RentACar getRentacar() {
		return rentACar;
	}

	public void setRentacar(RentACar rentacar) {
		this.rentACar = rentacar;
	}



	public BranchLocations getBranch_locations() {
		return branch_locations;
	}



	public void setBranch_locations(BranchLocations branch_locations) {
		this.branch_locations = branch_locations;
	}
	
	
	
	
}
