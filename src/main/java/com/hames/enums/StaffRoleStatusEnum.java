package com.hames.enums;

public enum StaffRoleStatusEnum {

	ACTIVE_STAFFROLE("Active"),
	INACTIVE_STAFFROLE("Inactive");
	
	private String value;
	
	private StaffRoleStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
