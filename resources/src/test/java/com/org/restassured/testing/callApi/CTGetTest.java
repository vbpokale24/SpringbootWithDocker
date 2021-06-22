package com.org.restassured.testing.callApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.org.restassured.testing.configuration.APITestingConnection;
import com.org.service.model.PersonModel;
import com.org.testHelper.ReadObjectFromFile;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * Below cases marked as ignore, because it should not be part of the mvn clean install part.
 * It gets used on local for rest assured framework
 */
public class CTGetTest {

	Gson gson = new Gson();
	List<PersonModel> testDataHolder = new ArrayList<>();
	Map<String, String> tokenHolder = new HashMap<>();
	@Ignore
	@Test
	public void createPerson() {
		RequestSpecification httpRequest = APITestingConnection.connection();
		String authToken = GetAuthToken.getAuthToken().getSubjectToken();
		tokenHolder.put("token", authToken);
		Header h2 = new Header("Authorization", authToken);
		Header h3 = new Header("Content-Type", "application/json");
		List<Header> list2 = new ArrayList<Header>();
		list2.add(h2);
		list2.add(h3);
		Headers header2 = new Headers(list2);
		httpRequest.headers(header2);
		PersonModel personRequestPayload = ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json",
				PersonModel.class);
		httpRequest.body(personRequestPayload);
		Response response = httpRequest.post("/person");
		PersonModel person = gson.fromJson(response.getBody().asString(), PersonModel.class);
		testDataHolder.add(person);
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 201);
		assertNotNull(person.getPerson().get(0).getId());
	}

	@Test
	@Ignore
	public void GetPersons() {
		RequestSpecification httpRequest = APITestingConnection.connection();
		Header h2 = new Header("Authorization", tokenHolder.get("token"));
		List<Header> list2 = new ArrayList<Header>();
		list2.add(h2);
		Headers header2 = new Headers(list2);
		httpRequest.headers(header2);
		Response response = httpRequest.get("/person");
		PersonModel person = gson.fromJson(response.getBody().asString(), PersonModel.class);
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		assertEquals(person.getPerson().get(0).getFirstName(), testDataHolder.get(0).getPerson().get(0).getFirstName());

	}

	@Test
	@Ignore
	public void deletePerson() {
		RequestSpecification httpRequest = APITestingConnection.connection();
		Header h2 = new Header("Authorization", GetAuthToken.getAuthToken().getSubjectToken());
		Header h3 = new Header("Content-Type", "application/json");
		List<Header> list2 = new ArrayList<Header>();
		list2.add(h2);
		list2.add(h3);
		Headers header2 = new Headers(list2);
		httpRequest.headers(header2);
		PersonModel personRequestPayload = ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json",
				PersonModel.class);
		httpRequest.body(personRequestPayload);
		Response response = httpRequest.post("/person");
	}

}
