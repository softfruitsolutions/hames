package com.hames.party.model;

public class Supplier extends Party {

	public String name;				//Specify Supplier Name
	public SupplierType type;
	public String address;
	public String contactNo;
	public String emailId;
	public String website;
	public String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SupplierType getType() {
		return type;
	}
	public void setType(SupplierType type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Supplier [name=" + name + ", type=" + type + ", address=" + address + ", contactNo=" + contactNo
				+ ", emailId=" + emailId + ", website=" + website + ", description=" + description + "]";
	}
	
}
