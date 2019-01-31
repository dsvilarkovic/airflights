package com.isa.airflights.model;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
import javax.persistence.OneToOne;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 *  @author Viktor
 *  Model abstract user-a
 * */

@Entity
@SequenceGenerator(name="seq", initialValue=6)
public class AbstractUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
	private Long id;
	
	@Column(name = "indexNumber", nullable = true)
	String index;
	
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
	
	@Column(name = "address", nullable = false)
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "verify", nullable = true)
	private Boolean verify;
	
	/**
	 * Polje koje oznacava kojeg servisa je admin ovaj korisnik
	 * 0 - nije nijednog, imace svaki korisnik koji se registruje
	 * */
	@Column(name = "id_company", nullable = false)
	private int idCompany;

	
	
	@OneToOne
	@JoinColumn(name = "id_airline", referencedColumnName = "id")
	private Airline airline;
	
	
	@Column(name = "last_password_reset_date")
	@JsonIgnore
    private Timestamp lastPasswordResetDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "abstractUser")
	private Set<VehicleReservation> vehicleReservation = new HashSet<VehicleReservation>();
	
	
	public AbstractUser() {
		
	}
	
	public AbstractUser(Long id, String firstName, String lastName, String email, String phoneNumber, String address,
			String password, Boolean v, int idCompany) {
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
	}


	public AbstractUser(AbstractUser user) {
		this(user.getId(), user.getFirstName(),user.getEmail(),user.getPassword(),user.getLastName(),user.getAddress(),user.getPhoneNumber(), user.getVerify(),user.getIdRentACar());
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

	@Override
	public String toString() {
		return "AbstractUser [id=" + id + ", index=" + index + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", password="
				+ password + ", verify=" + verify + ", idCompany=" + idCompany + ", airline=" + airline
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", roles=" + roles + "]";
	}
	
	
	
	
	
	
}
