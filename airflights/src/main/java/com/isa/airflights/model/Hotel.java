package com.isa.airflights.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = true)
	private String address;	
	
	@Column(name = "description", nullable = true, length = 2048)
	private String description;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "rating_count", nullable = false)
	private Long ratingsCount = 0L;
	
	@Column(name = "rating_sum", nullable = false)
	private Long ratingsSum = 0L;
	
	
	//@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JsonIgnoreProperties("hotel")
	//@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@Column(name = "hotel_id", nullable = false)
	//private Set<Room> rooms = new HashSet<Room>();
	
	/*@JsonIgnoreProperties("hotel")
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelExtras> extras = new HashSet<HotelExtras>();
	*/
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}*/
	
	/*public Set<HotelExtras> getExtras() {
		return extras;
	}

	public void setExtras(Set<HotelExtras> extras) {
		this.extras = extras;
	}*/
	
	public Long getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Long ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Long getRatingsSum() {
		return ratingsSum;
	}

	public void setRatingsSum(Long ratingsSum) {
		this.ratingsSum = ratingsSum;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hotel c = (Hotel) o;
        if(c.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return this.name;
	}

	public Hotel() {
		// Default
	}
	
}
