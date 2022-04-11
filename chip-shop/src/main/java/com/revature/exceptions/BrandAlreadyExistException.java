package com.revature.exceptions;

public class BrandAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BrandAlreadyExistException() {
		super();
	}

	public BrandAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BrandAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrandAlreadyExistException(String message) {
		super(message);
	}

	public BrandAlreadyExistException(Throwable cause) {
		super(cause);
	}
}
