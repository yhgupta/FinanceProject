package com.oracle.service.repayment;

import javax.ws.rs.core.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.exception.GeneralException;
import com.oracle.model.loanapplication.LoanApplication;
import com.oracle.model.repayment.Repayment;
import com.oracle.repository.loan.LoanRepo;
import com.oracle.repository.loanapplication.LoanApplicationRepo;
import com.oracle.repository.repayment.CustomerRepaymentRepo;
import com.oracle.service.repayment.impl.RepaymentServiceInterface;

public class RepaymentService implements RepaymentServiceInterface {

	@Override
	public Response repayLoanAmount(String jsonForRepayment) {
		try {
			JsonElement jsonElement = new JsonParser().parse(jsonForRepayment);
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			String applicationId = jsonObject.get("applicationId").getAsString();
			double amountPaid = jsonObject.get("amountPaid").getAsDouble();
			String uniqueReceiptId = jsonObject.get("verificationId").getAsString();

			CustomerRepaymentRepo customerRepaymentRepo = new CustomerRepaymentRepo();
			String[] data = customerRepaymentRepo.getLastPaidDate(applicationId);
			Long today = new java.util.Date().getTime();

			Long difference = today - Long.parseLong(data[0]);
			Long monthsDayLong = 1000L * 60L * 60L * 24L * 30L;
			int months = (int) Math.floor(difference / monthsDayLong);
			double amount = Double.parseDouble(data[1]);
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			LoanApplication loanApplication = loanApplicationRepo.getLoanApplicationById(applicationId);

			LoanRepo loanRepo = new LoanRepo();

			amount = amount * Math.pow((1 + loanRepo.getInterestRate(loanApplication.getLoanId()) / 1200),
					(((double) months / 12) * 12));
			amount = amount - amountPaid;

			Repayment repayment = new Repayment();
			repayment.setApplicationId(applicationId);
			repayment.setAmountPaidByCustomer(amountPaid);
			repayment.setAmountToBePaid(amount);
			repayment.setAmountPaidDate(new java.util.Date());
			repayment.setUniquePaymentString(uniqueReceiptId);

			if (customerRepaymentRepo.repaymentForTheLoan(repayment)) {
				return Response.status(200).entity("Loan RePayment Successfully").build();
			}
			else {
				throw new GeneralException("Loan Repayment Failed");
			}
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

	}


}
