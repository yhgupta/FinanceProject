package com.oracle.model.customer;

public class Customer {

	private String customerId;
	private String name;
	private String address;
	private String contactDetails;
	private String email;
	private int age;
	private String gender;

	public Customer() {

	}

	public Customer(String customerId, String name, String address, String contactDetails, String email, int age,
			String gender) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.contactDetails = contactDetails;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "\nPAN-CARD: " + this.getCustomerId() + "\nName: " + this.getName() + "\nAddress: " + this.getAddress()
				+ "\nContact Details: " + this.getContactDetails() + "\nEmail: " + this.getEmail() + "\nAge: "
				+ this.getAge() + "\nGender: " + this.getGender();
	}

}
