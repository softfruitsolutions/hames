package com.hames.enums;

public enum OrderType {

	SALE_ORDER("Sale Order"),
	PURCHASE_ORDER("Purchase Order");

	private String value;
	
	private OrderType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
