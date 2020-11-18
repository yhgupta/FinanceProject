package com.oracle.service.document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;
import com.oracle.exception.GeneralException;
import com.oracle.model.document.Document;
import com.oracle.repository.document.DocumentRepo;
import com.oracle.service.document.impl.DocumentServiceInterface;

public class DocumentService implements DocumentServiceInterface {


	@Override
	public void storeDocumentInDatabase(InputStream fileInputStream, String applicationId) {
		try {
			Document document = new Document();
			document.setApplicationId(applicationId);
			document.setDocumentId(fileInputStream);
			DocumentRepo documentRepo = new DocumentRepo();
			documentRepo.insertDocumentByApplicationId(document);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}

	@Override
	public List<InputStream> getAllDocumentForApplicationId(String applicationId) {
		DocumentRepo documentRepo = new DocumentRepo();
		return documentRepo.getDocumentByApplicationId(applicationId);
	}

	@Override
	public void storeDocumentInDB(JsonObject jsonObject, String applicationId) {
		String image1 = jsonObject.get("image1").getAsString();
		String image2 = jsonObject.get("image2").getAsString();
		String image3 = jsonObject.get("image3").getAsString();
		

		HashMap<String, List<String>> image = new HashMap<>();
		
		if (!image.containsKey(applicationId)) {
			image.put(applicationId, new ArrayList<String>());
		}
		image.get(applicationId).add(image1);
		image.get(applicationId).add(image2);
		image.get(applicationId).add(image3);

		System.out.println("Returned" + image.get(applicationId));

		for (String string : image.keySet()) {
			System.out.println(string);
		}
	}


}
