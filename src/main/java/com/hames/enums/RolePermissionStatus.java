package com.hames.enums;

public enum RolePermissionStatus {

	ACTIVE_ROLE("Active"),
	INACTIVE_ROLE("Inactive");
	
	private String value;
	
	private RolePermissionStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
