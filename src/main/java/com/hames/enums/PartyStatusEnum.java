package com.hames.enums;

public enum PartyStatusEnum {
	
	ACTIVE_CUSTOMER("Active Customer"),
	INACTIVE_CUSTOMER("Inactive Customer");
	
	private String text;
	
	private PartyStatusEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
