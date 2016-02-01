package com.hames.exception;

public class GenericServiceException extends RuntimeException{

	public GenericServiceException() {
		super();
	}

	public GenericServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericServiceException(String message) {
		super(message);
	}

	public GenericServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583543309511634307L;
	
}
