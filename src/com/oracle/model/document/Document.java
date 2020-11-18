package com.oracle.model.document;

import java.io.InputStream;

public class Document{
	private String applicationId;
	private InputStream documentId;
	
	public Document() {

	}
	
	public Document(String applicationId, InputStream documentId) {
		super();
		this.applicationId = applicationId;
		this.documentId = documentId;
	}
	
	public String getApplicationId() {
		return applicationId;
	}

	public InputStream getDocumentId() {
		return documentId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public void setDocumentId(InputStream documentId) {
		this.documentId = documentId;
	}
}