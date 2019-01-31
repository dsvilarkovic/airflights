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
	
	@Column(name = "description", nullable = true)
	private String description;
	
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
		return "Course [id=" + id + ", name=" + name + "]";
	}

	public Hotel() {
		// Default
	}
	
}
