package com.exception;

public class InvalidLoanException extends Exception {


	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidLoanException(String string) {
		this.message=string;
	}

	public String getMessage() {
		return message;
	}
	

}
