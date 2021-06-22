package com.org.restassured.testing.callApi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.org.restassured.testing.configuration.APITestingConnection;
import com.org.service.model.JWTTokenModel;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAuthToken {

	static Gson gson = new Gson();
	
	public static JWTTokenModel getAuthToken(){
		   RequestSpecification httpRequestForAuth = APITestingConnection.connectionForAuthToken();
		    Header h1= new Header("Basic", "c2VjdXJpdHlBZG1pbg==");
		    List<Header> list = new ArrayList<Header>();
		    list.add(h1);
		    Headers header = new Headers(list);
		    httpRequestForAuth.headers(header);
		    Response respAuth = httpRequestForAuth.get("/getJwtToken");
		    System.out.println(respAuth.asString());
		    return  gson.fromJson(respAuth.getBody().asString(), JWTTokenModel.class);	
	}
	
}
