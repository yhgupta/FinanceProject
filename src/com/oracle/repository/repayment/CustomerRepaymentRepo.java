package com.oracle.repository.repayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.GeneralException;
import com.oracle.model.repayment.Repayment;
import com.oracle.repository.repayment.impl.CustomerRepaymentInterface;

public class CustomerRepaymentRepo implements CustomerRepaymentInterface {

	@Override
	public double amountNeedToPayMore(String applicationId) {
		String[] data = getLastPaidDate(applicationId);
		return Double.parseDouble(data[1]);
	}

	public String[] getLastPaidDate(String applicationId) {
		String sqlQueryString = "select count(*) from customerPayment where applicationId = ?";
		String[] strings = new String[2];
		try (Connection connection = DatabaseConnection.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);
			preparedStatement.setString(1, applicationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getInt("count(*)") == 0) {

				sqlQueryString = "select approvedDate, amount from loanApplication where applicationId = ?";
				preparedStatement = connection.prepareStatement(sqlQueryString);
				preparedStatement.setString(1, applicationId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					strings[0] = String.valueOf(resultSet.getDate("approvedDate").getTime());
					strings[1] = String.valueOf(resultSet.getDouble("amount"));
				}

			} else {
				System.out.println("HERE");
				sqlQueryString = "select amountPaidDate, amountToBePaid from customerPayment where applicationId = ? order by amountPaidDate desc fetch first 1 rows only";
				preparedStatement = connection.prepareStatement(sqlQueryString);
				preparedStatement.setString(1, applicationId);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					strings[0] = String.valueOf(resultSet.getDate("amountPaidDate").getTime());
					strings[1] = String.valueOf(resultSet.getDouble("amountToBePaid"));
				}
			}
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}
		return strings;

	}

	@Override
	public boolean repaymentForTheLoan(Repayment repayment) {

		String sqlQueryString = "insert into customerpayment values(?,?,?,?,?)";
		int result = 0;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {

			preparedStatement.setString(1, repayment.getApplicationId());
			preparedStatement.setDouble(2, repayment.getAmountPaidByCustomer());
			preparedStatement.setDouble(3, repayment.getAmountToBePaid());
			preparedStatement.setDate(4, new java.sql.Date(repayment.getAmountPaidDate().getTime()));
			preparedStatement.setString(5, repayment.getUniquePaymentString());
			result = preparedStatement.executeUpdate();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}
		return result == 1;

	}


}
