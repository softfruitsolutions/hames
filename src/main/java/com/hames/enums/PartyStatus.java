package com.hames.enums;

public enum PartyStatus {
	
	ACTIVE_PARTY("Active Party"),
	INACTIVE_PARTY("Inactive Party");
	
	private String text;
	
	private PartyStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
