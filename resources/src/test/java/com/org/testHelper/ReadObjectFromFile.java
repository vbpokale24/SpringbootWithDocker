package com.org.testHelper;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ReadObjectFromFile {

	 private static final String JSON_FOLDER = "src/test/resources/UT/";

	    private static ObjectMapper mapper;

	    static {
	        mapper = new ObjectMapper();
	        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
	        mapper.registerModule(new JavaTimeModule());
	    }
	
	public static <T> T getObjectFromJson(String jsonFileName, Class<T> clazz) {

        T t = null;

        String filePath = JSON_FOLDER + jsonFileName;

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
            t = mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
        	e.getMessage();
        } 
        return t;
    }

}
