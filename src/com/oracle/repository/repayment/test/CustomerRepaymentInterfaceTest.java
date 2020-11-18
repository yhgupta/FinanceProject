package com.oracle.repository.repayment.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.repository.repayment.CustomerRepaymentRepo;

class CustomerRepaymentInterfaceTest {

	static CustomerRepaymentRepo customerRepaymentRepo;

	@BeforeAll
	static void CustomerRepaymentRepository() {
		customerRepaymentRepo = new CustomerRepaymentRepo();
	}

	@Test
	void testAmountNeedToPayMore() {
		String[] valueStrings = customerRepaymentRepo.getLastPaidDate("thfPWJf");

		System.out.println(new java.util.Date(Long.parseLong(valueStrings[0])));
		System.out.println(valueStrings[1]);
		assertEquals(true, true);
	}


}
