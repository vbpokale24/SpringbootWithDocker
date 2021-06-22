package com.org.resources.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class CreatePersonDelegateTest {

	@InjectMocks
	private CreatePersonDelegate createPersonDelegate;
	
	@Mock
	private ICreatePersonService createPersonService;
	
	@Test
	public void createPeople(){
		PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json", PersonResourceModel.class);
		when(createPersonService.addPerson(personRequestPayload)).thenReturn(personRequestPayload);
		ResponseEntity<PersonResourceModel> result = createPersonDelegate.addPerson(personRequestPayload);
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);;
	}
	
	@Test(expected = RuntimeException.class)	
	public void createPeopleWithNullData(){
		PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json", PersonResourceModel.class);
		when(createPersonService.addPerson(personRequestPayload)).thenReturn(personRequestPayload);
		ResponseEntity<PersonResourceModel> result = createPersonDelegate.addPerson(null);
	}
	
}
