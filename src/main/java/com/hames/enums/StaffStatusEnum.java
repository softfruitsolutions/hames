package com.hames.enums;

public enum StaffStatusEnum {
	
	ACTIVE_STAFF("Active Staff"),
	INACTIVE_STAFF("Inactive Staff");
	
	private String value;
	
	private StaffStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
