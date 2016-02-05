package com.hames.enums;

public enum ExpenseStatus {

	DRAFT("Draft"),
	ACTIVE("Active"),
	CANCELLED("Cancelled");
	
	private String text;
	private ExpenseStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
