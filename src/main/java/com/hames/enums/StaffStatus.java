package com.hames.enums;

public enum StaffStatus {
	
	ACTIVE_STAFF("Active Staff"),
	INACTIVE_STAFF("Inactive Staff");
	
	private String value;
	
	private StaffStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
