package com.oracle.exception;

@SuppressWarnings("serial")
public class CustomerNotFound extends RuntimeException {

	public CustomerNotFound() {

	}

	public CustomerNotFound(String string) {
		super(string);
	}

}
