package com.hames.inventory.enums;

public enum UnitOfMeasure {

	NOS("Nos"),
	KG("Kg"),
	EACH("Each");
	
	private String text;
	
	private UnitOfMeasure(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
