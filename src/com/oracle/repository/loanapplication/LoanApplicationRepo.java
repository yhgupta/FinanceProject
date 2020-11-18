package com.oracle.repository.loanapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.GeneralException;
import com.oracle.model.loanapplication.LoanApplication;
import com.oracle.repository.loanapplication.impl.LoanApplicationInterface;

public class LoanApplicationRepo implements LoanApplicationInterface {

	@Override
	public LoanApplication getLoanApplicationById(String applicationId) {
		String sqlQueryString = "select * from loanApplication where applicationId = ?";

		List<LoanApplication> list = new ArrayList<>();
		LoanApplication loanApplication = null;
		ResultSet resultSet = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {

			preparedStatement.setString(1, applicationId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String applicationIdString = resultSet.getString("applicationId");
				String customerIdString = resultSet.getString("customerId");
				int loanId = resultSet.getInt("loanId");
				double amount = resultSet.getDouble("amount");
				int repaymentTime = resultSet.getInt("repaymentTime");
				Date appliedDate = new java.util.Date(resultSet.getDate("appliedDate").getTime());
				Date approvedDate = new java.util.Date(resultSet.getDate("approvedDate").getTime());
				int applicationStatus = resultSet.getInt("applicationStatus");
				loanApplication = new LoanApplication(applicationIdString, customerIdString, loanId, amount,
						repaymentTime, appliedDate, approvedDate, applicationStatus);
				return loanApplication;
			}
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}
		if (loanApplication == null) {
			throw new GeneralException("Application Id Not Found");
		}
		return loanApplication;
	}

	@Override
	public List<LoanApplication> getLoanApplication() {
		List<LoanApplication> list = new ArrayList<>();
		String sqlQueryString = "select * from loanApplication";
		ResultSet resultSet = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {

			resultSet = preparedStatement.executeQuery();
			list = fromResultSet(resultSet);
		} catch (SQLException e) {
			throw new GeneralException(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean newapplication(LoanApplication loanApplication) {
		String sqlQueryString = "insert into loanApplication values(?,?,?,?,?,?,?,?)";
		int result = 0;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {

			preparedStatement.setString(1, loanApplication.getApplicationId());
			preparedStatement.setString(2, loanApplication.getCustomerId());
			preparedStatement.setInt(3, loanApplication.getLoanId());
			preparedStatement.setDouble(4, loanApplication.getAmount());
			preparedStatement.setInt(5, loanApplication.getRepaymentTime());
			preparedStatement.setDate(6, new java.sql.Date(loanApplication.getAppliedDate().getTime()));
			preparedStatement.setDate(7, new java.sql.Date(loanApplication.getApprovedDate().getTime()));
			preparedStatement.setInt(8, loanApplication.getApplicationStatus());
			result = preparedStatement.executeUpdate();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
		return result == 1;
	}

	@Override
	public void updateApplicationStatus(String applicationId, int status) {
		String sqlQueryString = " update loanApplication set applicationStatus = ? , approvedDate = ? where applicationId = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {

			preparedStatement.setInt(1, status);
			preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			preparedStatement.setString(3, applicationId);
			preparedStatement.executeUpdate();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	private LoanApplication loanApplicationFromResultSet(ResultSet resultSet) throws SQLException {
		LoanApplication loanApplication = null;
		String applicationIdString = resultSet.getString("applicationId");
		String customerIdString = resultSet.getString("customerId");
		int loanId = resultSet.getInt("loanId");
		double amount = resultSet.getDouble("amount");
		int repaymentTime = resultSet.getInt("repaymentTime");
		Date appliedDate = new java.util.Date(resultSet.getDate("appliedDate").getTime());
		Date approvedDate = new java.util.Date(resultSet.getDate("approvedDate").getTime());
		int applicationStatus = resultSet.getInt("applicationStatus");
		loanApplication = new LoanApplication(applicationIdString, customerIdString, loanId, amount, repaymentTime,
				appliedDate, approvedDate, applicationStatus);
		return loanApplication;
	}

	private List<LoanApplication> fromResultSet(ResultSet resultSet) {
		List<LoanApplication> loanApplications = new ArrayList<>();
		try {
			while (resultSet.next()) {
				loanApplications.add(loanApplicationFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loanApplications;
	}

}
