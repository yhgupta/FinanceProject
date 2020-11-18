package com.oracle.model.loan;

public class Loan {

	private int loanId;
	private String typeOfLoan;
	private double interestRate;

	public Loan() {

	}

	public Loan(int loanId, String typeOfLoan, double interestRate) {
		super();
		this.loanId = loanId;
		this.typeOfLoan = typeOfLoan;
		this.interestRate = interestRate;
	}

	public int getLoanId() {
		return loanId;
	}

	public String getTypeOfLoan() {
		return typeOfLoan;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public void setTypeOfLoan(String typeOfLoan) {
		this.typeOfLoan = typeOfLoan;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
