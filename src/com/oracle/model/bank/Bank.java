package com.oracle.model.bank;

public class Bank {

	private String customerId;
	private String bankName;
	private String bankBranch;
	private String customerAccountNumber;
	private String ifscCode;

	public Bank() {

	}

	public Bank(String customerId, String bankName, String bankBranch, String customerAccountNumber, String ifscCode) {
		super();
		this.customerId = customerId;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.customerAccountNumber = customerAccountNumber;
		this.ifscCode = ifscCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

}
