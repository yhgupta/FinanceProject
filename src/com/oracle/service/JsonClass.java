package com.oracle.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonClass {

	public static String parsePanData(String value) {
		JsonElement jsonElement = new JsonParser().parse(value);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String panString = jsonObject.get("panCard").getAsString();
		System.out.println(panString);
		return panString;
	}

	public static String parseApplicationIdData(String value) {
		JsonElement jsonElement = new JsonParser().parse(value);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String applicationIdString = jsonObject.get("applicationId").getAsString();
		System.out.println(applicationIdString);
		return applicationIdString;
	}



}
