package com.oracle.service.bank.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.oracle.exception.GeneralException;
import com.oracle.service.bank.BankService;

class BankServiceInterfaceTest {

	@Test
	void test() {
		BankService bankService = new BankService();
		Response response = bankService.creditAmount("4j55yIQ");
		System.out.println(response.getEntity());
		assertEquals(200, response.getStatus());
	}

	@Test
	void InvalidId() {
		BankService bankService = new BankService();
		assertThrows(GeneralException.class, () -> bankService.creditAmount("HVGm1Ya"));
	}

}
