package com.oracle.repository.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.database.DatabaseConnection;
import com.oracle.exception.GeneralException;
import com.oracle.model.document.Document;
import com.oracle.repository.document.impl.DocumentInterface;


public class DocumentRepo implements DocumentInterface {

	@Override
	public boolean insertDocumentByApplicationId(Document document) {
		String sqlQueryString = "insert into document values(?,?)";
		int result = 0;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {
			preparedStatement.setString(1, document.getApplicationId());
			preparedStatement.setBlob(2, document.getDocumentId());
			result = preparedStatement.executeUpdate();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
		return result == 1;
	}

	@Override
	public List<InputStream> getDocumentByApplicationId(String applicationId) {
		String sqlQueryString = "select documentId from document where applicationId = ?";

		List<InputStream> list = new ArrayList<>();

		ResultSet resultSet = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryString);) {
			preparedStatement.setString(1, applicationId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Blob blob = resultSet.getBlob("documentId");
				byte[] bytes = resultSet.getBytes("documentId");
				InputStream inputStream = blob.getBinaryStream();

				/*
				 * ByteArrayOutputStream buffer = new ByteArrayOutputStream(); int nRead; byte[]
				 * data = new byte[16384];
				 * 
				 * while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				 * buffer.write(data, 0, nRead); }
				 * 
				 * String encode = Base64.getEncoder().encodeToString(buffer.toByteArray());
				 */

				File file = new File("src/images/document.jpg");

				FileOutputStream outputStream = new FileOutputStream(file);
				int read;
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.flush();
				list.add(inputStream);
			}
		} catch (SQLException | IOException e) {
			throw new GeneralException(e.getMessage());
		}
		return list;
	}
}
