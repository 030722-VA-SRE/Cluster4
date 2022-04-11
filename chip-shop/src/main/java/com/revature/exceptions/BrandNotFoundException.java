package com.revature.exceptions;

public class BrandNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BrandNotFoundException() {
		super();
	}

	public BrandNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BrandNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrandNotFoundException(String message) {
		super(message);
	}

	public BrandNotFoundException(Throwable cause) {
		super(cause);
	}
	
}//end
