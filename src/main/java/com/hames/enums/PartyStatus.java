package com.hames.enums;

public enum PartyStatus {
	
	ACTIVE_CUSTOMER("Active Party"),
	INACTIVE_CUSTOMER("Inactive Party");
	
	private String text;
	
	private PartyStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
