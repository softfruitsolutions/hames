package com.hames.enums;

public enum PartyTypeEnum {

	SUPPLIER("Supplier"),
	CUSTOMER("Customer");
	
	private PartyTypeEnum(String text) {
		this.text = text;
	}

	private String text;
	
	public String getText() {
		return text;
	}
}
