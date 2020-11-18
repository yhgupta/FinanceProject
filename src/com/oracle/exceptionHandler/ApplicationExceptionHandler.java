package com.oracle.exceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.oracle.exception.ApplicationIdNotFound;

public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationIdNotFound> {

	@Override
	public Response toResponse(ApplicationIdNotFound applicationIdNotFound) {
		String jsonString = "{\"msg\":" + applicationIdNotFound.getMessage() + "}";
		return Response.status(404).entity(jsonString).build();
	}

}
