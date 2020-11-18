package com.oracle.repository.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.GeneralException;
import com.oracle.repository.loan.impl.LoanInterface;

public class LoanRepo implements LoanInterface {

	@Override
	public String getLoanType(int loanId) {
		String sqlQueryString = "select typeOfLoan from loan where loanId = ?";
		String type = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {
			preparedStatement.setInt(1, loanId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				type = resultSet.getString("typeOfLoan");
			}
			return type;

		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public double getInterestRate(int loanId) {
		String sqlQueryString = "select interestRate from loan where loanId = ?";
		double interestRate = 0;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {
			preparedStatement.setInt(1, loanId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				interestRate = resultSet.getDouble("interestRate");
			}
			return interestRate;
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}


}
