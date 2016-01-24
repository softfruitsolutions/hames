package com.hames.enums;

public enum PaymentItemStatus {
	
	ACTIVE_PAYMENT_ITEM("Active"),
	INACTIVE_PAYMENT_ITEM("Inactive");
	
	private String value;

	private PaymentItemStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
