package com.oracle.repository.document.impl;

import java.io.InputStream;
import java.util.List;

import com.oracle.model.document.Document;

public interface DocumentInterface {

	public boolean insertDocumentByApplicationId(Document document);

	public List<InputStream> getDocumentByApplicationId(String applicationId);

	
}
