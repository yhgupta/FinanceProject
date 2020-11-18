package com.oracle.service.customer.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.service.customer.CustomerService;

class CustomerServiceInterfaceTest {

	static CustomerService customerServiceRepository = null;

	@BeforeAll
	static void customerServiceInit() {
		customerServiceRepository = new CustomerService();
	}

	@Test
	void testAddNewCustomer() {
		/*
		 * String jsonString =
		 * "{\"customerId\":\"BYG0643A\",\"name\":\"Yash\",\"address\":\"Ghati Jin\",\"contactDetails\":\"3487629283\",\"email\":\"yhgupta8274@gmail.com\",\"age\":23,\"gender\":\"M\"}"
		 * ; assertEquals(200,
		 * customerServiceRepository.addNewCustomer(jsonString).getStatus());
		 */

	}

	@Test
	void testGetCustomerByCustomerId() {
		assertEquals(200, customerServiceRepository.getCustomerByCustomerId("BYQWG0643A").getStatus());
	}

	@Test
	void testGetCustomerByApplicationId() {
		assertEquals(200, customerServiceRepository.getCustomerByApplicationId("4j55yIQ").getStatus());
	}

}
