package com.oracle.repository.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.GeneralException;
import com.oracle.model.customer.Customer;
import com.oracle.repository.customer.impl.CustomerInterface;

public class CustomerRepo implements CustomerInterface{

	@Override
	public boolean newCustomer(Customer customer) {

		System.out.println(customer.getCustomerId());

		String sqlQueryString = "insert into customer values(?,?,?,?,?,?,?)";
		int result = 0;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, customer.getCustomerId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getContactDetails());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setInt(6,customer.getAge());
			preparedStatement.setString(7, customer.getGender());
			result = preparedStatement.executeUpdate();	
			connection.setAutoCommit(true);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
		return result == 1;
	};
	

	@Override
	public Customer getCustomerByApplicationId(String applicationId) {
		String sqlQueryString = "select * from customer C join loanApplication L using(customerId) where applicationId = ?";
		Customer customer = null;
		ResultSet resultSet = null;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, applicationId);
			resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ) {
				String customerId = resultSet.getString("customerId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String contactDetails = resultSet.getString("contactDetails");
				String email = resultSet.getString("email");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				customer = new Customer (customerId,  name, address, contactDetails,email,age,gender );
			}
			
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}

		return customer;
	}

	@Override
	public Customer getCustomerByCustomerId(String customerId) {

		String sqlQueryString = "select * from customer where customerId = ?";
		Customer customer = null;
		ResultSet resultSet = null;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, customerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String customerIdentify = resultSet.getString("customerId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String contactDetails = resultSet.getString("contactDetails");
				String email = resultSet.getString("email");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				customer = new Customer(customerIdentify, name, address, contactDetails, email, age, gender);
			}
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}		
		return customer;
	}
}