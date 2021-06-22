package com.org.restassured.testing.callApi;

import com.org.restassured.testing.configuration.APITestingConnection;
import com.org.service.model.PersonModel;

import io.restassured.response.Response;

public class CallAPI {

	public static Response getCall() {
        Response response = APITestingConnection.connection().get("/person");			
		return response;
	}
	
	public static Response createStudent(final PersonModel person) {
        Response response = APITestingConnection.connection().contentType("application/json").body(person).post("/person");		
		return response;
	}
}
