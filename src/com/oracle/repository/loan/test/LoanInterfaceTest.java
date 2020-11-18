package com.oracle.repository.loan.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.repository.loan.LoanRepo;

class LoanInterfaceTest {

	static LoanRepo loanRepo = null;

	@BeforeAll
	static void loanRepositoryInit() {
		loanRepo = new LoanRepo();
	}

	@Test
	void testGetLoanType() {
		String valueString = loanRepo.getLoanType(1);
		assertEquals("Vehicle", valueString);
	}

	@Test
	void testGetLoanInterestRate() {
		double value = loanRepo.getInterestRate(2);
		assertEquals(8.95, value);
	}

}
