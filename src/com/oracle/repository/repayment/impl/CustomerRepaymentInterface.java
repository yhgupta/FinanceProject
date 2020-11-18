package com.oracle.repository.repayment.impl;

import com.oracle.model.repayment.Repayment;

public interface CustomerRepaymentInterface {

	public double amountNeedToPayMore(String applicationId);
	
	public boolean repaymentForTheLoan(Repayment repayment);
	
}
