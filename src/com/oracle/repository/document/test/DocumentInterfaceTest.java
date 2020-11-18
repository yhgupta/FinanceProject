package com.oracle.repository.document.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.model.document.Document;
import com.oracle.repository.document.DocumentRepo;
import com.oracle.repository.document.impl.DocumentInterface;

class DocumentInterfaceTest {

	static DocumentInterface documentRepository = null;

	@BeforeAll
	static void documentRepositoryInit() {
		documentRepository = new DocumentRepo();
	}

	@Test
	void testInsertDocumentByApplicationId() {
		Document document = new Document();
		document.setApplicationId("KonFHD7");
		try {
			document.setDocumentId(new FileInputStream(new File("src/images/fox.jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(true, documentRepository.insertDocumentByApplicationId(document));
	}

	@Test
	void testGetDocumentByApplicationId() throws FileNotFoundException {
		documentRepository.getDocumentByApplicationId("thfPWJf");
		
		/*
		 * File file1 = new File("src/images/fox.jpg"); InputStream inputStream = new
		 * FileInputStream(file1); List<InputStream> list =
		 * documentRepository.getDocumentByApplicationId("KonFHD7");
		 * assertEquals(inputStream, list.get(0) );
		 */
		  
		}

}
