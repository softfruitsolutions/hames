package com.hames.bean;

import org.springframework.data.annotation.Id;

import com.hames.enums.StaffStatus;

public class Staff extends BaseBean {
	
	@Id
	private String staffId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String city;
	private String country;
	private String phoneNumber;
	private String mobileNumber;
	private String emailId;
	private String permanentAddress;
	private String temporaryAddress;
	private StaffStatus status;
	
	//private String roleId;

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public StaffStatus getStatus() {
		return status;
	}
	public void setStatus(StaffStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", city=" + city + ", country=" + country + ", phoneNumber="
				+ phoneNumber + ", mobileNumber=" + mobileNumber + ", emailId="
				+ emailId + ", permanentAddress=" + permanentAddress
				+ ", temporaryAddress=" + temporaryAddress + ", status="
				+ status + "]";
	}
	
	
}
