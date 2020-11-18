package com.oracle.repository.customerbank.impl;

import com.oracle.model.bank.Bank;

public interface CustomerBankInterface {

	public Bank getBankForCustomerId(String customerId);

	public Bank getBankForApplicationId(String applicationId);

	public boolean addNewBankAccount(Bank bank);

}
