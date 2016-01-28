package com.hames.exception;

public class StaffException extends RuntimeException{

	public StaffException() {
		super();
	}

	public StaffException(String message, Throwable cause) {
		super(message, cause);
	}

	public StaffException(String message) {
		super(message);
	}

	public StaffException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583543309511634307L;
	
}
