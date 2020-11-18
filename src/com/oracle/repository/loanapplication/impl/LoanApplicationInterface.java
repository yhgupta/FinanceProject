package com.oracle.repository.loanapplication.impl;

import java.util.List;

import com.oracle.model.loanapplication.LoanApplication;

public interface LoanApplicationInterface {

	public LoanApplication getLoanApplicationById(String applicationId);

	public List<LoanApplication> getLoanApplication();

	public boolean newapplication(LoanApplication loanApplication);

	public void updateApplicationStatus(String applicationId, int status);

}

