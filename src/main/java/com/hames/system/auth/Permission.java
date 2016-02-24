package com.hames.system.auth;

public enum Permission {
	
	/**
	 * Order
	 */
	VIEW_ORDER("View Order","order:view"),
	VIEW_SALE_ORDER("View Sale Order", "order:saleorder:view"),
	CREATE_SALE_ORDER("Create Sale Order","order:saleorder:create"),
	VIEW_SALE_ORDER_PAYMENT("View Sale Order Payment","order:saleorder:payment:view"),
	VIEW_SALE_ORDER_REPORT("View Sale Order Report","order:saleorder:report:view"),
	
	/**
	 * CUSTOMER
	 */
	VIEW_CUSTOMER("View Customer", "party:customer:view"),
	CREATE_CUSTOMER("Create Customer", "party:customer:create"),
	
	/**
	 * STAFF
	 */
	VIEW_STAFF("View Staff","hr:staff:view"),
	CREATE_STAFF("Create Staff","hr:staff:create"),
	
	VIEW_ADMINISTRATOR("View Administrator","admin:view"),
	/**
	 * ROLE
	 */
	VIEW_ROLE_PERMISSION("View Role Permission","admin:rolepermission:view"),
	CREATE_ROLE_PERMISSION("Create Role Permission","admin:rolepermission:create"),
	
	/**
	 * USER ACCOUNT
	 */
	VIEW_USER_ACCOUNT("View User Account","admin:useraccount:view"),
	CREATE_USER_ACCOUNT("Create User Account","admin:useraccount:create"),
	
	/**
	 * EXPENSE MANAGER
	 */
	VIEW_EXPENSE_MANAGER("View Expense Manager","expense:manager:view"),
	CREATE_EXPENSE_MANAGER("Create Expense Manager","expense:manager:create"),
	VIEW_EXPENSE_CATEGORY("View Expense Category","expense:category:view"),
	CREATE_EXPENSE_CATEGORY("Create Expense Category","expense:category:create");
	
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
