package com.oracle.service.repayment.impl;

import javax.ws.rs.core.Response;

public interface RepaymentServiceInterface {

	public Response repayLoanAmount(String json);

}
