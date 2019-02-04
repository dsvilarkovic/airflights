package com.isa.airflights.dto;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.Role;

/**
 * DTO sluzi da sakrije sifru pri slanju ka korisniku
 * @author Dusan
 *
 */
public class AbstractUserDTO{
	private Long id;
	private String index;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber; 
	private String address;
	private Integer idCompany;
	private boolean verify;
	private Role role;
	private Hotel hotel;
	private Airline airline;
	private String password;
	private String newPassword;
	
	public AbstractUserDTO() {
	
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Integer getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}
	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public AbstractUserDTO(AbstractUser user) {
		this.id = user.getId();
		this.index = user.getIndex();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.address = user.getAddress();
		this.idCompany = user.getIdCompany();
		this.verify = user.getVerify();
		this.role = user.getRole();
		this.hotel = user.getHotel();
		this.airline = user.getAirline();
		this.password = getPassword();
	}
	

}
