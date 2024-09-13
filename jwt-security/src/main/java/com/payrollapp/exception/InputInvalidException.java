package com.payrollapp.exception;

public class InputInvalidException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public InputInvalidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
