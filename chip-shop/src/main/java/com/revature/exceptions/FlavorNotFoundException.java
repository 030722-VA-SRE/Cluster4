package com.revature.exceptions;

public class FlavorNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FlavorNotFoundException() {
		super();
	}

	public FlavorNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FlavorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlavorNotFoundException(String message) {
		super(message);
	}

	public FlavorNotFoundException(Throwable cause) {
		super(cause);
	}

}
