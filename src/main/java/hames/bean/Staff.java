package hames.bean;

import hames.core.bean.BaseBean;

public class Staff extends BaseBean {
	
	private Long staffId;
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
	private Integer status;
	
	private Long roleId;

	
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
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
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getTemporaryAddress() {
		return temporaryAddress;
	}
	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
				+ status + ", roleId=" + roleId + "]";
	}
	
	
}
