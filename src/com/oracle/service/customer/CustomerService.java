package com.oracle.service.customer;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.exception.GeneralException;
import com.oracle.model.customer.Customer;
import com.oracle.repository.customer.CustomerRepo;
import com.oracle.service.bank.BankService;
import com.oracle.service.customer.impl.CustomerServiceInterface;

public class CustomerService implements CustomerServiceInterface {

	@Override
	public Response getCustomerByCustomerId(String customerId) {
		try {
			CustomerRepo customerRepo = new CustomerRepo();
			Customer customer = customerRepo.getCustomerByCustomerId(customerId);
			Gson gson = new Gson();
			return Response.status(200).entity(gson.toJson(customer)).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public Response getCustomerByApplicationId(String applicationId) {
		try {
			CustomerRepo customerRepo = new CustomerRepo();
			Customer customer = customerRepo.getCustomerByApplicationId(applicationId);
			Gson gson = new Gson();
			return Response.status(200).entity(gson.toJson(customer)).build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}


	@Override
	public Response addNewCustomer(String customerDataInJson) {

		JsonElement jsonElement = new JsonParser().parse(customerDataInJson);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		System.out.println("Outside try");

		try {
			System.out.println("Inside try");

			String customerId = jsonObject.get("customerId").getAsString();
			String name = jsonObject.get("name").getAsString();
			String address = jsonObject.get("address").getAsString();
			String contactDetails = jsonObject.get("contactDetails").getAsString();
			String email = jsonObject.get("email").getAsString();
			int age = jsonObject.get("age").getAsInt();
			String gender = jsonObject.get("gender").getAsString();


			System.out.println("After object");
			Customer customer = new Customer(customerId, name, address, contactDetails, email, age, gender);

			BankService bankService = new BankService();
			bankService.addCustomerBankAccount(jsonObject);

			System.out.println("Here");


			CustomerRepo customerRepo = new CustomerRepo();
			boolean value = customerRepo.newCustomer(customer);
			return Response.status(200).entity("Customer Added successfully").build();
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public Response userExist(String customerId) {
		try {
			CustomerRepo customerRepo = new CustomerRepo();
			Customer customer = customerRepo.getCustomerByCustomerId(customerId);
			if (customer != null) {
				return Response.status(200).entity(customer.getCustomerId()).build();
			} else {
				return Response.status(200).entity("no").build();
			}
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

}