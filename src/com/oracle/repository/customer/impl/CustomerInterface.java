package com.oracle.repository.customer.impl;

import com.oracle.model.customer.Customer;

public interface CustomerInterface {
	
	public boolean newCustomer(Customer customer);
	
	public Customer getCustomerByApplicationId(String applicationId);
	
	public Customer getCustomerByCustomerId(String customerId);
}