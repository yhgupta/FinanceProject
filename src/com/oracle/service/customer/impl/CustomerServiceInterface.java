package com.oracle.service.customer.impl;

import javax.ws.rs.core.Response;

public interface CustomerServiceInterface {

	public Response addNewCustomer(String customerDataInJson);

	public Response getCustomerByCustomerId(String customerId);

	public Response getCustomerByApplicationId(String applicationId);

	public Response userExist(String customerId);

}
