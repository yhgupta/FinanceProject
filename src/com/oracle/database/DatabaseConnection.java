package com.oracle.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			String urlString = "jdbc:oracle:thin:@localhost:1521/orcl";
			String usernameString = "admin";
			String passwordString = "orclorcl";
			connection = DriverManager.getConnection(urlString, usernameString, passwordString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
