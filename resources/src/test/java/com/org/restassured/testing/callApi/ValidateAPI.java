package com.org.restassured.testing.callApi;

import com.org.service.model.JWTTokenModel;
import com.org.service.model.PersonResourceModel;

public class ValidateAPI {

	
	public Boolean validateTest(String expected, String actual) {
		if(expected.equals(actual)) {
		return true;
		}
		else {
			return false;
		}			
	}
	
	public Boolean validateJWTApiCall(JWTTokenModel  tokenData) {
		if(tokenData.getSubjectToken()==null){
			return false;
		}else
		{
			return true;
		}
		
	}
	
	public Boolean validateTestForPostCall(PersonResourceModel  person) {
	    if(person!=null){
	    	return false;
	    }else{
	    	return true;
	    }
	}
}
