package com.hames.enums;

public enum CustomerStatusEnum {
	
	ACTIVE_CUSTOMER("Active Customer"),
	INACTIVE_CUSTOMER("Inactive Customer");
	
	private String text;
	
	private CustomerStatusEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
