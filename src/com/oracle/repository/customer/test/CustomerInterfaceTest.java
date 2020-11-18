package com.oracle.repository.customer.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.model.customer.Customer;
import com.oracle.repository.customer.CustomerRepo;
import com.oracle.repository.customer.impl.CustomerInterface;

class CustomerInterfaceTest {
	static CustomerInterface customerRepository =null;

	@BeforeAll
	static void customerRepositoryInit() {
		customerRepository = new CustomerRepo();
	}

	@Test
	void testGetCustomerByApplicationId() {
		Customer customer = customerRepository.getCustomerByApplicationId("KonFHD7");
		assertEquals("BYQWG0643A", customer.getCustomerId());
	}
	
	@Test
	void testGetCustomerByCustomerId() {
		Customer customer = customerRepository.getCustomerByCustomerId("BYQWG0643A");
		assertEquals("yfhsudi@gamil.com", customer.getEmail());
	}
	
	@Test
	void testNewCustomer() {
		/*
		 * Customer customer = new Customer(); customer.setCustomerId("BYQWG0643A");
		 * customer.setName("Yash Gupta"); customer.setAddress("Nutan nagar");
		 * customer.setContactDetails("962628462");
		 * customer.setEmail("yfhsudi@gamil.com"); customer.setAge(23);
		 * customer.setGender("M"); assertEquals(true,
		 * customerRepository.newCustomer(customer));
		 */
	}

}
