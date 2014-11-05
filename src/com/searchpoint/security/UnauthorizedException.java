package com.searchpoint.security;

/**
 * Exception which represents unauthorized user action.
 * 
 * @author Igors Gulbinskis
 */
public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String message) {
		super(message);
	}
	
}
