#**Project Title:: 
 This is a Spring Boot REST API application for CRUD operation. This project consist of three maven module(Resources,Service and persistence). For maven clean build, please always use **org-Parent** module.If you are using STS/eclipse to run the project, please run the Application.java class as spring boot application.Or you can follow the step given in Getting started and installment.

#**Key Features**\
1)As per requirement, Both endpoints are implemented(Create Employee/Person and Change the state of person/employee)
2)Swagger-ui will be present to understand the contract between API. In addition it can be useful for testing.
3)Spring Sleuth has used for generating the tracing id in logging.
4)Rest assured framework has used for CT(Component Testing) testing. Please see **Testing** Section for generating the report  and more info about CT.
5)All API's are secured by JWT token. Please see API description for more info.
6)Actuator of spring has implemented for health status,bean info etc..
7)Additional Features::a)Other CRUD operational API is provided for employee.                       

#**Assumptions**\
1)Change state API only change the state when state flow is getting followed.For e.g. State can not be changed from ADDED to ACTIVE, it should be changed from ADDED-->INCHECK-->APPROVED-->ACTIVE. If we try to change from ADDED to ACTIVE, will get exception.
2) In Patch API, state should be same as present in current data. State can not be patched by this API because seperate changeStatus API has provided for this.

#**Prerequisites**\
JDK 8 \
Maven \
STS[Version3.8.4](optional) \

#**Getting Started and Installment**
Copy this project folder in your workspace.
	
Make sure your workspace is JDK/JRE Enabled. 

Do maven build on parent folder. 

**For maven build.** \
a)Go to cmd ->go to folder_name of project\

b)Type mvn clean install or mvn install \
 note::(Need to set maven home first if installing mvn)
 
c)Go to resources module target folder.

**RUN The Project.** \
 java -jar resources-0.0.1-SNAPSHOT.jar note:(Check the execute permission)\



Your development server is ready on the console.

**Another way to generate jar.** \
a)import project in IDE. \
b)right click on project(org-parent)->Run as->maven build -> in Goals type clean install. \
c)Now jar is generated in target folder of resources module.\
 


#Deployment \
To deploy the project on the production, Follow same run rules mentioned above.

#**Testing**
 1) After running the jar(java -jar resources-0.0.1-SNAPSHOT.jar), hit http://localhost:8080/swagger-ui.html#/. You will get all API's. You can test from swagger also.
    Note:: Please generate the token by using **Get JWT token** API, and use for subsequent calls.
	       JWT token basic value is c2VjdXJpdHlBZG1pbg==
           For Employee related API use	, don't use Bearer word before JWT token in Authorization header.	   
 2) *At parent project, postman collection is present.Please import that collection into postman.*\  
 3) RestAssured Testing::\
    a) CT(component testing)is written for this project. \
    b)To generate the CT report, please run the ExecuteAPITester.java class. It is present in resource module test. This class wil generate the report.(Please make sure service is up and running before running the class)\
 4) Junit is written for this project in resource module.\

#**API Description**

 This Api is secured by **JWT**.You need to use To access the api, user need to pass the Authotization header in request.\
 **Import the postman collection name embl-rest-Api.postman_collection.json in postman for getting all API in postman.Json collection   present in project itself.** \
 

## 1)Get the authorization token API::
 	 Url::http://localhost:8080/securitymanagement/v1/getJwtToken
 	 Method Type :: GET
 	 Header Parameter:: 
      			1)Content-Type :application/json
	  	2)Basic		 :c2VjdXJpdHlBZG1pbg==
	  
	  Response ::
	       {
	  "subjectToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTgwMTQwMDU3LCJzdWIiOiJBdXRob3JpemF0aW9uIiwiaXNzIjoiQWRtaW4iLCJleHAiOjE1ODAxNDM1MTR9.GybmBJIovQi7HAR5MeooI5cbrnDMg5_q7fRgYMTPAdo"
	      }
  #### Basic token value is c2VjdXJpdHlBZG1pbg==	     
  ###****Note: above token will be used to access the API.If token gets expired, user needs to generate the new token.****

## 2)Create the persons API::
  	Url::http://localhost:8080/personmanagement/v1/person
  	Method Type:: POST
  	Header Parameter:: 
      			1)Content-Type : application/json
	  	        2)Authorization		 : << Above api token generated i.e. subjectToken >>
  
 	 Request Payload::    
	    {
	      "firstName": "Vaibhav",
	      "lastName": "Pokale",
	      "age": "29",
	      "favouriteColor": "Blue",
	      "hobby": [
		"Chess",
		"Cricket"
	      ]
		}
	 
	 
   	Response Payload::   
        {
		  "id":1,
		  "state":"ADDED"
	      "firstName": "Vaibhav",
	      "lastName": "Pokale",
	      "age": "29",
	      "favouriteColor": "Blue",
	      "hobby": [
		"Chess",
		"Cricket"
	      ]
		}

## 3) Change State person API::
   	Url::  http://localhost:8080/personmanagement/v1/person/{personID}
   	Method Type:: POST
   	Header Parameter:: 
      		1)Content-Type : application/json
	        2)Authorization		 : << Above api token generated i.e. subjectToken >>
  	Request Payload::  
			 { 
			  "state":"INCHECK"
			}
	Response Payload::
	     {
		  "id":1,
		  "state":"INCHECK"
	      "firstName": "Vaibhav",
	      "lastName": "Pokale",
	      "age": "29",
	      "favouriteColor": "Blue",
	      "hobby": [
		"Chess",
		"Cricket"
	      ]
		}
		
	
## 4) Get person against person_id API::	
    Url::http://localhost:8080/personmanagement/v1/person/1
	Method Type:: GET
    Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
    
	Response Payload::
     {
		  "id":1,
		  "state":"ADDED"
	      "firstName": "Vaibhav",
	      "lastName": "Pokale",
	      "age": "29",
	      "favouriteColor": "Blue",
	      "hobby": [
		"Chess",
		"Cricket"
	      ]
		}  

## 5) Patch the person data API::
    Url:: http://localhost:8080/personmanagement/v1/person/1
	Method Type:: Patch
    Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
	
	Request Payload::		
    {
	  "state":"INCHECK"
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Red",
      "hobby": [
        "Chess",
        "Cricket"
      ]
    }    
  	Response Payload::   		
	    {
	      "id": 1,
		  "state":"INCHECK"
	      "firstName": "Vaibhav",
	      "lastName": "Pokale",
	      "age": "29",
	      "favouriteColor": "Red",
	      "hobby": [
		"Chess",
		"Cricket"		
	      ]
	    }


## 6) Delete the person against the person_id API::
    	Url:: http://localhost:8080/personmanagement/v1/person/1
	Method Type:: Delete
   	Header Parameter:: 
      		1)Content-Type : application/json
	  	2)Authorization		 : << Above api token generated i.e. subjectToken >>
	
	Response Body:: HTTP STATUS code 204.

	
	   

Built With
[CMD] Command Client
Maven - Dependency Management
Versioning
1.0.0

Authors
Vaibhav Pokale
