package com.nhuszka.web.exception;

public class IllegalServletParameterException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IllegalServletParameterException(String message) {
		super(message);
	}
}
