package com.hames.enums;

public enum StaffRoleStatus {

	ACTIVE_STAFFROLE("Active"),
	INACTIVE_STAFFROLE("Inactive");
	
	private String value;
	
	private StaffRoleStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
