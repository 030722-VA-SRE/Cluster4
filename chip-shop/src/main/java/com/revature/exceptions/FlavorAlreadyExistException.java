package com.revature.exceptions;

import org.springframework.stereotype.Repository;

@Repository
public class FlavorAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FlavorAlreadyExistException() {
		super();
	}

	public FlavorAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FlavorAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlavorAlreadyExistException(String message) {
		super(message);
	}

	public FlavorAlreadyExistException(Throwable cause) {
		super(cause);
	}

}//end
