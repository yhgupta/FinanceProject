package com.oracle.service.bank;

import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.oracle.exception.GeneralException;
import com.oracle.model.bank.Bank;
import com.oracle.model.loanapplication.LoanApplication;
import com.oracle.repository.customerbank.CustomerBankRepo;
import com.oracle.repository.loanapplication.LoanApplicationRepo;
import com.oracle.service.bank.impl.BankServiceInterface;

public class BankService implements BankServiceInterface {

	@Override
	public Response creditAmount(String applicationId) {
		System.out.println("Inside credit method");
		try {

			CustomerBankRepo customerBankRepo = new CustomerBankRepo();
			Bank bank = customerBankRepo.getBankForApplicationId(applicationId);
			System.out.println(bank.getCustomerId());
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			LoanApplication loanApplication = loanApplicationRepo.getLoanApplicationById(applicationId);
			System.out.println(loanApplication.getCustomerId());
			if (loanApplication.getCustomerId().equals(bank.getCustomerId())) {
				return Response.status(200).entity("Amount Has been Credited").build();

			}
			throw new GeneralException("Verification is not done");
		}catch ( Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public void addCustomerBankAccount(JsonObject jsonObject) {

		String customerId = jsonObject.get("customerId").getAsString();
		String bankName = jsonObject.get("bankName").getAsString();
		String bankBranch = jsonObject.get("bankBranch").getAsString();
		String customeraAccount = jsonObject.get("accountNumber").getAsString();
		String ifscCode = jsonObject.get("ifscCode").getAsString();
		Bank bank = new Bank();

		bank.setCustomerId(customerId);
		bank.setBankName(bankName);
		bank.setBankBranch(bankBranch);
		bank.setCustomerAccountNumber(customeraAccount);
		bank.setIfscCode(ifscCode);

		System.out.println("Acc " + bank.getCustomerAccountNumber());
		System.out.println("Cust " + bank.getCustomerId());

		CustomerBankRepo customerBankRepo = new CustomerBankRepo();
		customerBankRepo.addNewBankAccount(bank);

	}

}
