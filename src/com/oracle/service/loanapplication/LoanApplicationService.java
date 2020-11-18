package com.oracle.service.loanapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.email.Email;
import com.oracle.exception.GeneralException;
import com.oracle.model.customer.Customer;
import com.oracle.model.loanapplication.LoanApplication;
import com.oracle.repository.loanapplication.LoanApplicationRepo;
import com.oracle.service.customer.CustomerService;
import com.oracle.service.document.DocumentService;
import com.oracle.service.loanapplication.impl.LoanApplicationServiceInterface;

public class LoanApplicationService implements LoanApplicationServiceInterface {

	@Override
	public String generateApplicationId() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		int n = 7;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}


	@Override
	public Response getLoanApplication(String applicationId) {

		String jsonString = "";
		try {
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			LoanApplication loanApplication = loanApplicationRepo.getLoanApplicationById(applicationId);

			System.out.println(loanApplication.getApplicationId());
			Gson gson = new Gson();
			jsonString = gson.toJson(loanApplication);
			System.out.println(jsonString);
			System.out.println(jsonString);
			return Response.status(200).entity(jsonString).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

	}

	@Override
	public Response getAllLoanApplicationStatusWise() {
		try {
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			List<LoanApplication> list = loanApplicationRepo.getLoanApplication();
			list.sort((a, b) -> Integer.compare(b.getApplicationStatus(), a.getApplicationStatus()));
			List<String> returnList = new ArrayList<>();

			for (LoanApplication loanApplication : list) {
				if (loanApplication.getApplicationStatus() == 1) {
					returnList.add(loanApplication.getApplicationId() + " Processing ");
				} else if (loanApplication.getApplicationStatus() == 2) {
					returnList.add(loanApplication.getApplicationId() + " Not Approved ");
				} else {
					returnList.add(loanApplication.getApplicationId() + " Approved ");
				}
			}

			Gson gson = new Gson();
			return Response.status(200).entity(gson.toJson(returnList)).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public Response getAllLoanApplicationDateWise() {
		try {
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			List<LoanApplication> list = loanApplicationRepo.getLoanApplication();
			list.sort((a, b) -> Long.compare(b.getApprovedDate().getTime(), a.getApprovedDate().getTime()));
			List<String> returnList = new ArrayList<>();
			for (LoanApplication loanApplication : list) {
				if (loanApplication.getApplicationStatus() == 3) {
				returnList.add(
							loanApplication.getApplicationId() + " Approved Date: " + loanApplication.getAppliedDate());
				}
			}
			Gson gson = new Gson();
			return Response.status(200).entity(gson.toJson(returnList)).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public Response addLoanApplication(String jsonForLoanApplication) {
		JsonElement jsonElement = new JsonParser().parse(jsonForLoanApplication);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		try {
			String applicationId = generateApplicationId();
			String customerId = jsonObject.get("customerId").getAsString();
			int loanId = jsonObject.get("loanId").getAsInt();
			// getDataForSpecificLoan(jsonObject, loanId);
			double amount = jsonObject.get("amount").getAsDouble();
			int repaymentTime = jsonObject.get("repaymentTime").getAsInt();
			Date appliedDate = new java.util.Date();
			Date approvedDate = new java.util.Date();
			int applicationStatus = 1;

			DocumentService documentService = new DocumentService();
			documentService.storeDocumentInDB(jsonObject, applicationId);

			LoanApplication loanApplication = new LoanApplication(applicationId, customerId, loanId, amount, repaymentTime,
					appliedDate, approvedDate, applicationStatus);
	
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			loanApplicationRepo.newapplication(loanApplication);

			CustomerService customerService = new CustomerService();

			Response response = customerService.getCustomerByApplicationId(applicationId);
			Gson gson = new Gson();
			Customer customer = gson.fromJson((String) response.getEntity(), Customer.class);
			System.out.println(customer.getCustomerId());
			Email email = new Email();
			email.sendEmailToUser(customer, applicationId);

			return Response.status(200).entity(loanApplication.getApplicationId()).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public Response updateStatus(String json) {
		JsonElement jsonElement = new JsonParser().parse(json);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String applicationId = jsonObject.get("applicationId").getAsString();
		int status = jsonObject.get("status").getAsInt();

		try {
			LoanApplicationRepo loanApplicationRepo = new LoanApplicationRepo();
			loanApplicationRepo.updateApplicationStatus(applicationId, status);
			Email email = new Email();

			return Response.status(200).entity("Successfully Updated").build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

}
