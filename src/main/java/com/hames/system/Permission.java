package com.hames.system;

public enum Permission {
	
	VIEW_STAFF("View Staff","staff:view"),
	CREATE_STAFF("Create Staff","staff:create");
	
	private String text;
	private String permission;
	
	private Permission(String text, String permission) {
		this.text = text;
		this.permission = permission;
	}

	public String getText() {
		return text;
	}

	public String getPermission() {
		return permission;
	}
	
}
