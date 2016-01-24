package com.hames.enums;

public enum PaymentItemType {

	CASH("Cash"),
	CREDIT_CARD("Credit Card");
	
	private String value;
	
	private PaymentItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
