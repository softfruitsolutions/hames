package com.hames.inventory.enums;

public enum ProductStatus {

	ACTIVE("Active"),
	IN_ACTIVE("Inactive");
	
	private String text;
	
	ProductStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
