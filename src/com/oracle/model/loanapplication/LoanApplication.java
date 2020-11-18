package com.oracle.model.loanapplication;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoanApplication {

	private String applicationId;
	private String customerId;
	private int loanId;
	private double amount;
	private int repaymentTime;
	private Date appliedDate;
	private Date approvedDate;
	private int applicationStatus;

	public LoanApplication() {

	}

	public LoanApplication(String applicationId, String customerId, int loanId, double amount, int repaymentTime,
			Date appliedDate, Date approvedDate, int applicationStatus) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanId = loanId;
		this.amount = amount;
		this.repaymentTime = repaymentTime;
		this.approvedDate = approvedDate;
		this.appliedDate = appliedDate;
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public int getLoanId() {
		return loanId;
	}

	public double getAmount() {
		return amount;
	}

	public int getRepaymentTime() {
		return repaymentTime;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public int getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setRepaymentTime(int repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}
