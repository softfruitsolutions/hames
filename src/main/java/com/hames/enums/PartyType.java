package com.hames.enums;

public enum PartyType {

	SUPPLIER("Supplier"),
	CUSTOMER("Customer");
	
	private PartyType(String text) {
		this.text = text;
	}

	private String text;
	
	public String getText() {
		return text;
	}
}
