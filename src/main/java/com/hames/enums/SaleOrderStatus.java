package com.hames.enums;

public enum SaleOrderStatus {
	
	DRAFT("Draft"),
	CREATED("Created"),
	PROOFING("Proofing"),
	PROOF_APPROVED("Proof Approved"),
	IN_PROGRESS("In Progress"),
	COMPLETED("Completed"),
	DELIVERED("Delivered"),
	ON_HOLD("On Hold");
	
	private String value;
	
	private SaleOrderStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
