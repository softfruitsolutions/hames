package com.hames.enums;

public enum PaymentStatus {
	
	PENDING("Pending"),
	PARTIALLY_PAID("Partially Paid"),
	PAID("Paid");
	
	private String value;

	private PaymentStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
