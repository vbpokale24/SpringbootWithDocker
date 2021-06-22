package com.org.restassured.testing.callApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.org.restassured.testing.configuration.APITestingConnection;
import com.org.restassured.testing.report.CreateReport;
import com.org.restassured.testing.report.ReportModel;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.JWTTokenModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/*
 * This class will generate the API testing report in simple HTML format under resources module.
 * Html file name is report.html.
 * 
 */
public class ExecuteAPITester {	
	     Gson gson = new Gson();		
		ValidateAPI validateAPI = new ValidateAPI();		
		List testDataHolder= new ArrayList<>();
		
		Map<String, String> tokenHolder  =  new HashMap<>();
		public static void main(String[] args) throws IOException {
			ExecuteAPITester executeAPITester = new ExecuteAPITester();
			List<ReportModel> reportModelList = new ArrayList<>();	
			reportModelList.add(executeAPITester.getAuthToken());
			reportModelList.add(executeAPITester.createPerson());
			reportModelList.add(executeAPITester.getPerson());
			reportModelList.add(executeAPITester.changeStatusFromAddedToInCheck());
			reportModelList.add(executeAPITester.patchPerson());
			reportModelList.add(executeAPITester.deletPerson());
			CreateReport.createReport(reportModelList);
		}
		
		public ReportModel getAuthToken() {
			ReportModel reportModel = new ReportModel();
			JWTTokenModel  authToken = GetAuthToken.getAuthToken();
			Boolean isValid = validateAPI.validateJWTApiCall(authToken);
			reportModel.setResult(String.valueOf(isValid));
			reportModel.setTestName("Get Auth Token");
			reportModel.setResponse(gson.toJson(authToken));
			reportModel.setRequest(gson.toJson("http://localhost:8080/securitymanagement/v1/getJwtToken"));
			return reportModel;
		}
		
		public ReportModel createPerson() {
			ReportModel reportModel = new ReportModel();
			 RequestSpecification httpRequest = APITestingConnection.connection();
			 String authToken=  GetAuthToken.getAuthToken().getSubjectToken();
			 tokenHolder.put("token", authToken);
	         Header h2= new Header("Authorization", authToken);
	         Header h3= new Header("Content-Type", "application/json");
			    List<Header> list2 = new ArrayList<Header>();
			    list2.add(h2);
			    list2.add(h3);
			    Headers header2 = new Headers(list2);
			    httpRequest.headers(header2);
			    PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json", PersonResourceModel.class);
			    httpRequest.body(personRequestPayload);
			    Response response = httpRequest.post("/person");
			    PersonResourceModel person = gson.fromJson(response.getBody().asString(), PersonResourceModel.class);
			    testDataHolder.add(person);
			    int statusCode = response.getStatusCode();
			    assertEquals(statusCode, 201);
			    assertNotNull(person.getId());
			Boolean isValid = validateAPI.validateTestForPostCall(person);
			reportModel.setResult(String.valueOf(isValid));
			reportModel.setTestName("Create Person");
			reportModel.setResponse(response.asString());
			reportModel.setRequest(gson.toJson(personRequestPayload));
			return reportModel;
		}
		
		public ReportModel  getPerson(){
			ReportModel reportModel = new ReportModel();
			RequestSpecification httpRequest = APITestingConnection.connection();
            Header h2= new Header("Authorization", tokenHolder.get("token"));
		    List<Header> list2 = new ArrayList<Header>();
		    list2.add(h2);
		    Headers header2 = new Headers(list2);
		    httpRequest.headers(header2);
		    Response response = httpRequest.get("/person");	
		    int statusCode = response.getStatusCode();
		    assertEquals(statusCode, 200);
		    reportModel.setResult("true");
			reportModel.setTestName("get Person List");
			reportModel.setResponse(response.asString());
			reportModel.setRequest(APITestingConnection.base_url+"/person");
			return reportModel;
		}
		
		public ReportModel  changeStatusFromAddedToInCheck(){
			ReportModel reportModel = new ReportModel();
			RequestSpecification httpRequest = APITestingConnection.connection();
            Header h2= new Header("Authorization", tokenHolder.get("token"));
            Header h3= new Header("Content-Type", "application/json");
		    List<Header> list2 = new ArrayList<Header>();
		    list2.add(h2);
		    list2.add(h3);
		    Headers header2 = new Headers(list2);
		    httpRequest.headers(header2);
		    ChangeStatusPersonModel changeStatePayload =ReadObjectFromFile.getObjectFromJson("StatusChangeToInCheck.json", ChangeStatusPersonModel.class);	
		    httpRequest.body(changeStatePayload);
		    PersonResourceModel person = (PersonResourceModel) testDataHolder.get(testDataHolder.size()-1);
		    Response response = httpRequest.post("/person/"+person.getId());
		    int statusCode = response.getStatusCode();
		    assertEquals(statusCode, 200);
		    reportModel.setResult("true");
			reportModel.setTestName("Change Status from Added to InCheck");
			reportModel.setResponse(response.asString());
			reportModel.setRequest(APITestingConnection.base_url+"/person");
			return reportModel;
		}
		
		public ReportModel  patchPerson(){
			ReportModel reportModel = new ReportModel();
			RequestSpecification httpRequest = APITestingConnection.connection();
            Header h2= new Header("Authorization", tokenHolder.get("token"));
            Header h3= new Header("Content-Type", "application/json");
		    List<Header> list2 = new ArrayList<Header>();
		    list2.add(h2);
		    list2.add(h3);
		    Headers header2 = new Headers(list2);
		    httpRequest.headers(header2);
		    PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("PatchPersonPayload.json", PersonResourceModel.class);
		    httpRequest.body(personRequestPayload);
		    PersonResourceModel person = (PersonResourceModel) testDataHolder.get(testDataHolder.size()-1);
		    Response response = httpRequest.patch("/person/"+person.getId());	
		    int statusCode = response.getStatusCode();
		    assertEquals(statusCode, 200);
		    reportModel.setResult("true");
			reportModel.setTestName("Patch person");
			reportModel.setResponse(response.asString());
			reportModel.setRequest(APITestingConnection.base_url+"/person/"+person.getId());
			return reportModel;
		}
		public ReportModel  deletPerson(){
			ReportModel reportModel = new ReportModel();
			RequestSpecification httpRequest = APITestingConnection.connection();
            Header h2= new Header("Authorization", tokenHolder.get("token"));
            Header h3= new Header("Content-Type", "application/json");
		    List<Header> list2 = new ArrayList<Header>();
		    list2.add(h2);
		    list2.add(h3);
		    Headers header2 = new Headers(list2);
		    httpRequest.headers(header2);
		    PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("PatchPersonPayload.json", PersonResourceModel.class);
		    httpRequest.body(personRequestPayload);
		    PersonResourceModel person = (PersonResourceModel) testDataHolder.get(0);
		    Response response = httpRequest.delete("/person/"+person.getId());	
		    reportModel.setResult("true");
			reportModel.setTestName("Delete person");
			reportModel.setResponse("No content");
			reportModel.setRequest(APITestingConnection.base_url+"/person/"+person.getId());
			return reportModel;
		}
}
