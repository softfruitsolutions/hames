package com.hames.exception;

public class ExpenseManagerException extends RuntimeException{

	public ExpenseManagerException() {
		super();
	}

	public ExpenseManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpenseManagerException(String message) {
		super(message);
	}

	public ExpenseManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583543309511634307L;
	
}
