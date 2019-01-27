package com.isa.airflights.dto;

import com.isa.airflights.model.AbstractUser;

/**
 * DTO sluzi da sakrije sifru pri slanju ka korisniku
 * @author Dusan
 *
 */
public class AbstractUserDTO{
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String roles;
	private String phoneNumber; 
	private String address;
	private int idCompany;
	private boolean verify;
	
	//TODO @Dusan: companyId i ostala sranja izmeniti
	/**
	 * DTO sluzi da sakrije sifru pri slanju ka korisniku
	 * @author Dusan
	 *
	 */
	public AbstractUserDTO(Long id, String firstName, String lastName, String email, String password, String roles,
			String phoneNumber, String address, boolean verify, int idCompany) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		//this.roles = roles; //TODO @Viktor i @Dusan
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.verify = verify;
		this.idCompany = idCompany;
	}
	
	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	/**
	 * Kopi konstruktor za brisanje sifre
	 * @param abstractUser
	 */
	public AbstractUserDTO(AbstractUser abstractUser) {
		this.id = abstractUser.getId();
		this.firstName = abstractUser.getFirstName();
		this.lastName = abstractUser.getLastName();
		this.email = abstractUser.getEmail();
		this.password = abstractUser.getPassword();
		//TODO @Dusan i Djuka : roles izmeniti sta bi trebalo ovde da stoji
		this.phoneNumber = abstractUser.getPhoneNumber();
		this.address = abstractUser.getAddress();
		this.idCompany = abstractUser.getIdRentACar();
	}
	
	public AbstractUser getAbstractUser() {		
		//TODO @Djuka : dodati roles
		return new AbstractUser(id, firstName, lastName, email, phoneNumber, address, password, verify, idCompany);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
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

	

}
