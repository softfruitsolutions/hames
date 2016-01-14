package com.hames.enums;

public enum OrderTypeEnum {

	SALE_ORDER("Sale Order"),
	PURCHASE_ORDER("Purchase Order");

	private String value;
	
	private OrderTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
