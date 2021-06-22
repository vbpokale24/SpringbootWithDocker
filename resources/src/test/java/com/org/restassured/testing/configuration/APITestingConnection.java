package com.org.restassured.testing.configuration;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
public class APITestingConnection {

	public static final String base_url = "http://localhost:8080/personmanagement/v1";
	
	public  static final String base_url_auth_token  = "http://localhost:8080/securitymanagement/v1";

	public static RequestSpecification connection() {
		RestAssured.baseURI = base_url;
		RequestSpecification httpRequest = RestAssured.given();
		return httpRequest;
	}
	
	public static RequestSpecification connectionForAuthToken() {
		RestAssured.baseURI = base_url_auth_token;
		RequestSpecification httpRequest = RestAssured.given();
		return httpRequest;
	}
}
