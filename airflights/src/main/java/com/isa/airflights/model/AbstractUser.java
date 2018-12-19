package com.isa.airflights.model;


import javax.persistence.Entity;
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
import javax.persistence.OneToMany;

/**
 *  @author Viktor
 *  Model abstract user-a
 * */

@Entity
public class AbstractUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	/** @pdOid 4b1f425a-b666-46ed-8696-e88f32341055 */
	
	@Column(name = "address", nullable = false)
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "verify", nullable = false)
	private Boolean verify;
	
	/*
	 * role = 0 nije admin
	 * role = 1 admin aviokompanije
	 * role = 2 admin hotela
	 * role = 3 admin rent a car servisa
	 * */
	@Column(name = "role", nullable = true)
	private long role;
	
	public AbstractUser() {
		super();
	}
	
	public AbstractUser(Long id, String firstName, String lastName, String email, String phoneNumber, String address,
			String password, Boolean v, Long l) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
		this.verify = v;
		this.role = l;
	}


	public AbstractUser(AbstractUser user) {
		this(user.getId(), user.getFirstName(),user.getEmail(),user.getPassword(),user.getLastName(),user.getAddress(),user.getPhoneNumber(), user.getVerify(),user.getrole());
	}


	

	public long getrole() {
		return role;
	}

	public void setrole(long role) {
		this.role = role;
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
		this.password = password;
	}





	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}





	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}





	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	
	
	
	
	
}
