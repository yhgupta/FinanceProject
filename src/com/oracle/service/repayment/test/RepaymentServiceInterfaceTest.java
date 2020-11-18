package com.oracle.service.repayment.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.service.repayment.RepaymentService;

class RepaymentServiceInterfaceTest {

	static RepaymentService repaymentService;

	@BeforeAll
	static void RepaymentServiceRepo() {
		repaymentService = new RepaymentService();
	}

	@Test
	void testRepayLoanAmount() {
		String jsonString = "{\"applicationId\":\"bmXglXJ\",\"amountPaid\":56377.45,\"verificationId\":\"4djd8djw\"}";
		Response response = repaymentService.repayLoanAmount(jsonString);
		assertEquals(200, response.getStatus());
	}

}
