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

import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class RetrievePersonDelegateTest {

	@InjectMocks
	private RetrievePersonDelegate retrievePersonDelegate;

	@Mock
	private IRetrievePersonService retrievePersonService;

	PersonResourceModel personGetPayload;

	PersonModel personGetPayloadForAllPerson;

	@Before
	public void setUp() {
		personGetPayload = ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonResourceModel.class);
		personGetPayloadForAllPerson = ReadObjectFromFile.getObjectFromJson("GetAllPersonPayload.json",
				PersonModel.class);
	}

	@Test
	public void getPeopleByID() {
		when(retrievePersonService.getPersonById(Mockito.anyInt())).thenReturn(personGetPayload);
		ResponseEntity<PersonResourceModel> person = retrievePersonDelegate.getPersonById(1);
		assertEquals(person.getStatusCode(), HttpStatus.OK);
		assertEquals(person.getBody().getAge(), "29");
	}

	@Test
	public void getAllPeople() {
		when(retrievePersonService.getAllPerson()).thenReturn(personGetPayloadForAllPerson);
		ResponseEntity<PersonModel> person = retrievePersonDelegate.getAllPerson();
		assertEquals(person.getStatusCode(), HttpStatus.OK);
		assertEquals(person.getBody().getPerson().size(), 2);
	}
}
