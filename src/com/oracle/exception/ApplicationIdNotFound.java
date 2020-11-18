package com.oracle.exception;

@SuppressWarnings("serial")
public class ApplicationIdNotFound extends RuntimeException {

	public ApplicationIdNotFound(String string) {
		super(string);
	}

	public ApplicationIdNotFound() {

	}
}
