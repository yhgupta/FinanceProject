package com.oracle.service.loanapplication.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.exception.GeneralException;
import com.oracle.service.loanapplication.LoanApplicationService;

class LoanApplicationServiceInterfaceTest {

	static LoanApplicationService loanApplicationService;

	@BeforeAll
	static void LoanApplicationServiceRepository() {
		loanApplicationService = new LoanApplicationService();
	}

	@Test
	void testAddNewLoanApplication() {
		/*
		 * String jsonString =
		 * "{\"customerId\":\"BYQPG0643A\",\"loanId\":3,\"amount\":765394,\"repaymentTime\":1,\"appliedDate\":1602009000000}";
		 * assertEquals(200,
		 * loanApplicationService.addLoanApplication(jsonString).getStatus());
		 */
	}

	@Test
	void testGetLoanApplication() {
		assertEquals(200, loanApplicationService.getLoanApplication("{\"applicationId\":\"HN5gD8t\"}").getStatus());
	}

	@Test
	void testGetInvalidLoanApplication() {
		assertThrows(GeneralException.class, () -> loanApplicationService.getLoanApplication("346"));
	}

	@Test
	void testGetAllLoanApplicationStatusWise() {
		Response response = loanApplicationService.getAllLoanApplicationStatusWise();
	}

	@Test
	void testGetAllLoanApplicationDateWise() {
		Response response = loanApplicationService.getAllLoanApplicationDateWise();
		System.out.println(response.getEntity());

	}

}
