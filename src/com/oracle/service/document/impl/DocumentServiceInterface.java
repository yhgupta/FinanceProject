package com.oracle.service.document.impl;

import java.io.InputStream;
import java.util.List;

import com.google.gson.JsonObject;

public interface DocumentServiceInterface {

	public void storeDocumentInDB(JsonObject jsonObject, String applicationId);

	public void storeDocumentInDatabase(InputStream fileInputStream, String applicationId);

	public List<InputStream> getAllDocumentForApplicationId(String applicationId);

}
