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

import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class PatchPersonDelegateTest {

	@InjectMocks
	private PatchPersonDelegate patchPersonDelegate;
	
	@Mock
	private IPatchPersonService patchPersonService;
	
	PersonResourceModel personGetPayload;
	
	@Mock
	PersonResourceModel persons;
	
	@Before
	public void setUp(){
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonResourceModel.class);
	}
	
	@Test
	public void testPatchDelegate(){
		when(patchPersonService.patchPerson(Mockito.anyInt(), Mockito.any(PersonResourceModel.class))).thenReturn(personGetPayload);
		ResponseEntity<PersonResourceModel>  person = patchPersonDelegate.patchPerson(1,persons);
		assertEquals(person.getStatusCode(), HttpStatus.OK);
		assertEquals(person.getBody().getAge(),"29");
	}
	
}
