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
	
	public static Boolean isValidEnum(OrderType orderType){
		for (OrderType ot : OrderType.values()) {
			if(orderType.equals(ot)){
				return true;
			}
		}
		return false;
	}
}
