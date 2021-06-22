package com.org.resources.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.interfaces.IChangeStatusPersonService;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class ChangeStatusDelegateTest {

	@InjectMocks
	private ChangeStatusDelegate changeStatusDelegate;
	
	@Mock
	private IChangeStatusPersonService changeStatusPersonService;
	
	ChangeStatusPersonModel changeStatusPersonModel;
	
	PersonResourceModel personGetPayload;
	
	@Before
	public void setUp(){
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonResourceModel.class);
	}
	
	@Test
	public void testChangeDelegateToInCheck(){
		changeStatusPersonModel = new ChangeStatusPersonModel();
		changeStatusPersonModel.setState("INCHECK");
		when(changeStatusPersonService.changeStatusPerson(Mockito.anyInt(), Mockito.any(ChangeStatusPersonModel.class))).thenReturn(personGetPayload);
		ResponseEntity<PersonResourceModel>  person = changeStatusDelegate.changeStatus(1,changeStatusPersonModel);
		assertEquals(person.getStatusCode(), HttpStatus.OK);
		assertEquals(person.getBody().getState(),"INCHECK");
	}
}
