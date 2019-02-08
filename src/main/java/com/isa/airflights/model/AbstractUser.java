package com.isa.airflights.model;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  @author Viktor
 *  Model abstract user-a
 * */

/**
 * @author Viktor
 *
 */
@Entity
@SequenceGenerator(name="seq", initialValue=6)
public class AbstractUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	private Long id;
	

	
	@Column(name = "indexNumber", nullable = true)
	private String index;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	/** @pdOid e0db3c3a-50d2-402c-b71c-973cbf62c53b */
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	/** @pdOid 4b5cfa10-6719-4561-919b-67a40409a869 */
	
	@Column(name = "email", nullable = false)
	private String email;
	/** @pdOid 961d918c-800c-4d13-9583-739c96511640 */
	
	@Column(name = "phoneNumber", nullable = true)
	private String phoneNumber;
	/** @pdOid 4b1f425a-b666-46ed-8696-e88f32341055 */
	
	@Column(name = "address", nullable = true)
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "verify", nullable = true)
	private Boolean verify;
	
	
	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private Set<Friendship> senders = new HashSet<>();
	
	@OneToMany(mappedBy = "receiver")
	@JsonIgnore
	private Set<Friendship> receiver = new HashSet<>();
	

	/**
	 * Polje koje oznacava kojeg servisa je admin ovaj korisnik
	 * 0 - nije nijednog, imace svaki korisnik koji se registruje
	 * */
	// @Djuka - idCompany je polje id rentacar servisa
	@Column(name = "id_rentacar", nullable = true)
	private Integer idCompany;
	
	
	//@Column(name = "id_company", nullable = true)
	//private RentACar idCompany;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Airline airline;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;
	
	@Column(name = "last_password_reset_date")
	@JsonIgnore
    private Timestamp lastPasswordResetDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OnDelete(action = OnDeleteAction.CASCADE) 
    private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "abstractUser")
	private Set<VehicleReservation> vehicleReservation = new HashSet<VehicleReservation>();
	
	
	/**
	 * Jedan korisnik moze imati vise karata za let, a jednu kartu moze imati samo jedan korisnik
	 */
	@OneToMany(mappedBy = "abstractUser", cascade = CascadeType.REFRESH)
	private Set<FlightTicket> flightTickets = new HashSet<>();
	
	/**
	 * Sluzi za rezervaciju karata putnicima koji ne postoje u sistemu
	 * @author dusan
	 */
	private Boolean isUnregistered = false;
	
	/**
	 * Polje za verifikaciju unosa nove lozinke kada se prvi put loguje
	 * true - promenio je lozinku kad se prvi put logovao,
	 * false - nije promenio lozinku kad se prvi put logovao
	 * */
	@Column(name = "change_pass", nullable = true)
	public boolean changePass = false;
	
	public AbstractUser() {
		
	}
	
	public AbstractUser(Long id, String firstName, String lastName, String email, String phoneNumber, String address,
			String password, Boolean v, int idCompany, boolean ch) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
		this.verify = v;
		this.idCompany = idCompany;
		this.changePass = ch;
	}


	public AbstractUser(AbstractUser user) {
		this(user.getId(), user.getFirstName(),user.getEmail(),user.getPassword(),user.getLastName(),user.getAddress(),user.getPhoneNumber(), user.getVerify(),user.getIdRentACar(),user.isChangePass());
	}


	public boolean isChangePass() {
		return changePass;
	}

	public void setChangePass(boolean changePass) {
		this.changePass = changePass;
	}

	public int getIdRentACar() {
		return idCompany;
	}

	public void setIdRentACar(int idRentACar) {
		this.idCompany = idRentACar;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.setLastPasswordResetDate( now );
        this.password = password;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
	
	public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }




	

	public Set<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(Set<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	

	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Set<VehicleReservation> getVehicleReservation() {
		return vehicleReservation;
	}

	public void setVehicleReservation(Set<VehicleReservation> vehicleReservation) {
		this.vehicleReservation = vehicleReservation;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Friendship> getSenders() {
		return senders;
	}

	public void setSenders(Set<Friendship> senders) {
		this.senders = senders;
	}

	public Set<Friendship> getReceiver() {
		return receiver;
	}

	public void setReceiver(Set<Friendship> receiver) {
		this.receiver = receiver;
	}
	
	
	
	public Boolean getIsUnregistered() {
		return isUnregistered;
	}

	public void setIsUnregistered(Boolean isUnregistered) {
		this.isUnregistered = isUnregistered;
	}

	@Override
	public String toString() {
		return "AbstractUser [id=" + id + ", index=" + index + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", password="
				+ password + ", verify=" + verify + ", idCompany=" + idCompany + ", airline=" + airline
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", roles=" + roles + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractUser abstractUser = (AbstractUser) o;
        if(abstractUser.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, abstractUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
	
	
	
	
}
