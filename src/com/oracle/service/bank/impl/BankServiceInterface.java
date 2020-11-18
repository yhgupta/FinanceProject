package com.oracle.service.bank.impl;

import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

public interface BankServiceInterface {
	
	public Response creditAmount(String applicationId);
	
	public void addCustomerBankAccount(JsonObject jsonObject);

}
