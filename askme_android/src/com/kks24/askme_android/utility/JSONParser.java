package com.kks24.askme_android.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {
	public static String convertToJSON(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = "";
		try {
			jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResponse = null;
		}
		return jsonResponse;
	}
	
	public static <T> T convertToObject(final TypeReference<T> type, final String dataString) {
	    T data = null;	 
	    try {
	    	data = new ObjectMapper().readValue(dataString, type);
	    } catch (Exception e) {	       
	    }
    	return data;
	}
}
