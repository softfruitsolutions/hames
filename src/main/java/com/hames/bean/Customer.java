package com.hames.bean;

public class Customer extends Party {

	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String mobileNumber;
	private String emailId;
	private String permanentAddress;
	private String temporaryAddress;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getTemporaryAddress() {
		return temporaryAddress;
	}
	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", mobileNumber=" + mobileNumber + ", emailId="
				+ emailId + ", permanentAddress=" + permanentAddress
				+ ", temporaryAddress=" + temporaryAddress + "]";
	}
}
