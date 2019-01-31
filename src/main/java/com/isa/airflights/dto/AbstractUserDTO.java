package com.isa.airflights.dto;

public class AbstractUserDTO {
	
	private String firstName;
	/** @pdOid e0db3c3a-50d2-402c-b71c-973cbf62c53b */
	private String lastName;
	/** @pdOid 4b5cfa10-6719-4561-919b-67a40409a869 */
	private String email;
	/** @pdOid 961d918c-800c-4d13-9583-739c96511640 */
	private String phoneNumber;
	/** @pdOid 4b1f425a-b666-46ed-8696-e88f32341055 */
	private String address;
	/** @pdOid efe39867-d211-48b9-9944-f39eb66d7b4e */
	private String password;
	
	
	
	public AbstractUserDTO(String firstName, String lastName, String email, String phoneNumber, String address,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
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
	
	
	
}
