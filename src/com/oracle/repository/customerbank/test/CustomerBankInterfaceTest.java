package com.oracle.repository.customerbank.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.exception.ApplicationIdNotFound;
import com.oracle.exception.CustomerNotFound;
import com.oracle.model.bank.Bank;
import com.oracle.repository.customerbank.CustomerBankRepo;

class CustomerBankInterfaceTest {

	static CustomerBankRepo customerBankRepo = null;

	@BeforeAll
	static void CustomerBankRepositoryInit() {
		customerBankRepo = new CustomerBankRepo();
	}

	@Test
	void testGetBankForCustomerId() {
		Bank bank = customerBankRepo.getBankForCustomerId("BYQWG0643A");
		assertEquals("7641963928635", bank.getCustomerAccountNumber());
	}

	@Test
	void testGetBankForInvalidCustomerId() {
		assertThrows(CustomerNotFound.class, () -> customerBankRepo.getBankForCustomerId("wgdug64537"));
	}

	@Test
	void testGetBankForInvalidApplicationId() {
		assertThrows(ApplicationIdNotFound.class, () -> customerBankRepo.getBankForApplicationId("gewfu92"));
	}

	@Test
	void testGetBankForApplicationId() {
		Bank bank = customerBankRepo.getBankForApplicationId("4j55yIQ");
		assertEquals("BYQWG0643A", bank.getCustomerId());
	}

	@Test
	void testAddNewBankAccount() {
		Bank bank = new Bank();
		bank.setCustomerId("ZVTAG0643A");
		bank.setBankName("KOTAK");
		bank.setBankBranch("MANIT BHOPAL");
		bank.setCustomerAccountNumber("24582926345235");
		bank.setIfscCode("KOTA0005463");
		System.out.println(bank);
		boolean value = customerBankRepo.addNewBankAccount(bank);
		assertEquals(true, value);
	}

}
