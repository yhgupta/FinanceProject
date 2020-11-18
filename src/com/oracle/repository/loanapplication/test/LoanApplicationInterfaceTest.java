package com.oracle.repository.loanapplication.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.exception.ApplicationIdNotFound;
import com.oracle.model.loanapplication.LoanApplication;
import com.oracle.repository.loanapplication.LoanApplicationRepo;

class LoanApplicationInterfaceTest {

	static LoanApplicationRepo loanRepository = null;

	@BeforeAll
	static void lonApplicationRepositoryInit() {
		loanRepository = new LoanApplicationRepo();
	}

	@Test
	void testGetLoanApplicationById() {
		/*
		 * LoanApplication loanApplication =
		 * loanRepository.getLoanApplicationById("thfPWJf"); assertEquals("thfPWJf",
		 * loanApplication.getApplicationId()); assertEquals("BYQWG0643A",
		 * loanApplication.getCustomerId()); assertEquals(3,
		 * loanApplication.getLoanId()); assertEquals(345837.32,
		 * loanApplication.getAmount()); assertEquals(1,
		 * loanApplication.getRepaymentTime()); assertEquals(new Date(1596220200000L),
		 * new java.util.Date(loanApplication.getAppliedDate().getTime()));
		 * assertEquals(new Date(1596479400000L), new
		 * java.util.Date(loanApplication.getApprovedDate().getTime())); assertEquals(1,
		 * loanApplication.getApplicationStatus());
		 */
	}

	@Test
	void testGetLoanApplicationByInvalidId() {
		assertThrows(ApplicationIdNotFound.class, () -> loanRepository.getLoanApplicationById("eutfisf"));
	}

	@Test
	void testGetLoanApplication() {
		List<LoanApplication> list = loanRepository.getLoanApplication();
		list.sort((a, b) -> Long.compare(b.getApprovedDate().getTime(), a.getApprovedDate().getTime()));
	}

	@Test
	void testGetLoanApplicationByApprovalStatus() {
		List<LoanApplication> list = loanRepository.getLoanApplication();
		list.sort((a, b) -> Integer.compare(b.getApplicationStatus(), a.getApplicationStatus()));
	}

	@Test
	void testNewapplication() {
		/*
		 * LoanApplicationService loanApplicationService = new LoanApplicationService();
		 * String applicationIdString = loanApplicationService.generateApplicationId();
		 * LoanApplication loanApplication = new LoanApplication(applicationIdString,
		 * "BYQWG0643A", 3, 345837.32, 1, new Date(1598380200000L), new
		 * Date(1598725800000L), 2); boolean value =
		 * loanRepository.newapplication(loanApplication); assertEquals(true, value);
		 */
	}

}
