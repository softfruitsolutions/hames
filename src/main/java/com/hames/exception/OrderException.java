package com.hames.exception;

public class OrderException extends RuntimeException{

	public OrderException() {
		super();
	}

	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderException(String message) {
		super(message);
	}

	public OrderException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583543309511634307L;
	
}
