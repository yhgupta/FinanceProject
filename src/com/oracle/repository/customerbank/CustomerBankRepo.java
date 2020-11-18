package com.oracle.repository.customerbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.ApplicationIdNotFound;
import com.oracle.exception.CustomerNotFound;
import com.oracle.exception.GeneralException;
import com.oracle.model.bank.Bank;
import com.oracle.repository.customerbank.impl.CustomerBankInterface;

public class CustomerBankRepo implements CustomerBankInterface {

	@Override
	public Bank getBankForCustomerId(String customerId) {
		String sqlQueryString = "select * from customerBankAccount where customerId = ?";

		Bank bank = null;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			bank = fromResultSet(resultSet);
			if (bank == null) {
				throw new CustomerNotFound("This is Invalid Customer Id");
			}

		} catch (Exception e) {
			throw new CustomerNotFound(e.getMessage());
		}
		return bank;
	}

	@Override
	public Bank getBankForApplicationId(String applicationId) {
		String sqlQueryString = "select * from customerBankAccount where customerId = ( select customerId from loanApplication where applicationId = ?)";
		Bank bank = null;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, applicationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			bank = fromResultSet(resultSet);
			if (bank == null) {
				throw new ApplicationIdNotFound("This is Invalid Application Id");
			}
		} catch (Exception e) {
			throw new ApplicationIdNotFound(e.getMessage());
		}

		return bank;
	}

	@Override
	public boolean addNewBankAccount(Bank bank) {
		String sqlQueryString = "insert into customerBankAccount values(?,?,?,?,?)";
		int result = 0;
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			/*
			 * if (checkWetherUserAccountExist(bank.getCustomerId())) { throw new
			 * GeneralException("User Details Already Exist"); }
			 */
			preparedStatement.setString(1, bank.getCustomerId());
			preparedStatement.setString(2, bank.getBankName());
			preparedStatement.setString(3, bank.getBankBranch());
			preparedStatement.setString(4, bank.getCustomerAccountNumber());
			preparedStatement.setString(5, bank.getIfscCode());
			result = preparedStatement.executeUpdate();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
		return result == 1;
	}

	private boolean checkWetherUserAccountExist(String customerId) throws SQLException {

		String sqlQueryString = "select count(*) from customerbankAccount where customerId = ?";
		try (Connection connection = DatabaseConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.getInt(1) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	private Bank fromResultSet(ResultSet resultSet) throws SQLException {
		Bank bank = null;
		if (resultSet.next()) {
			String customerIdentity = resultSet.getString("customerId");
			String bankName = resultSet.getString("bankName");
			String bankBranch = resultSet.getString("bankBranch");
			String customerAccountNumber = resultSet.getString("customerAccountNumber");
			String ifscCode = resultSet.getString("ifscCode");

			bank = new Bank(customerIdentity, bankName, bankBranch, customerAccountNumber, ifscCode);
		}
		return bank;
	}

}
