package com.oracle.exceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.oracle.exception.GeneralException;

public class GeneralExceptionHandler implements ExceptionMapper<GeneralException> {

	@Override
	public Response toResponse(GeneralException generalException) {
		String jsonString = "{\"msg\":" + generalException.getMessage() + "}";
		System.out.println(jsonString);
		return Response.status(404).entity(jsonString).build();
	}

}
