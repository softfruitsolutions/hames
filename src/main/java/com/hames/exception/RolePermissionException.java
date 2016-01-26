package com.hames.exception;

public class RolePermissionException extends RuntimeException{

	public RolePermissionException() {
		super();
	}

	public RolePermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RolePermissionException(String message) {
		super(message);
	}

	public RolePermissionException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583543309511634307L;
	
}
