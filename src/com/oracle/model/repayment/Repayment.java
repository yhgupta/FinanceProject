package com.oracle.model.repayment;

import java.util.Date;

public class Repayment {

	private String applicationId;
	private double amountPaidByCustomer;
	private double amountToBePaid;
	private Date amountPaidDate;
	private String uniquePaymentString;

	public Repayment() {

	}

	public Repayment(String applicationId, double amountPaidByCustomer, double amountToBePaid, Date amountPaidDate,
			String uniquePaymentString) {
		super();
		this.applicationId = applicationId;
		this.amountPaidByCustomer = amountPaidByCustomer;
		this.amountToBePaid = amountToBePaid;
		this.amountPaidDate = amountPaidDate;
		this.uniquePaymentString = uniquePaymentString;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public double getAmountPaidByCustomer() {
		return amountPaidByCustomer;
	}

	public void setAmountPaidByCustomer(double amountPaidByCustomer) {
		this.amountPaidByCustomer = amountPaidByCustomer;
	}

	public double getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	public Date getAmountPaidDate() {
		return amountPaidDate;
	}

	public void setAmountPaidDate(Date amountPaidDate) {
		this.amountPaidDate = amountPaidDate;
	}

	public String getUniquePaymentString() {
		return uniquePaymentString;
	}

	public void setUniquePaymentString(String uniquePaymentString) {
		this.uniquePaymentString = uniquePaymentString;
	}

}
