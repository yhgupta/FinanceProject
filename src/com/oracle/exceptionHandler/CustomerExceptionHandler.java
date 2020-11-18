package com.oracle.exceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.oracle.exception.CustomerNotFound;

public class CustomerExceptionHandler implements ExceptionMapper<CustomerNotFound> {

	@Override
	public Response toResponse(CustomerNotFound customerNotFound) {
		String jsonString = "{\"msg\":" + customerNotFound.getMessage() + "}";
		return Response.status(404).entity(jsonString).build();
	}

}
