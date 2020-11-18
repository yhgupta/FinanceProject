package com.oracle.service.loanapplication.impl;

import javax.ws.rs.core.Response;

public interface LoanApplicationServiceInterface {

	public Response addLoanApplication(String jsonForLoanApplication);

	public Response getLoanApplication(String applicationId);

	public Response getAllLoanApplicationStatusWise();

	public Response getAllLoanApplicationDateWise();

	public String generateApplicationId();

	public Response updateStatus(String json);

}
