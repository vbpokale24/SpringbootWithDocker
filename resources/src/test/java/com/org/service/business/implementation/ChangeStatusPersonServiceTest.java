package com.org.service.business.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.ValidationUtils;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.interfaces.implemetation.ChangeStatusPersonValidator;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class ChangeStatusPersonServiceTest {

	@InjectMocks
	ChangeStatusPersonService changeStatusPersonService;
	
	@Mock
	private IRetrievePersonService retrievePersonService;
	
	@Mock
	private PersonRepository personRepository;
	
	PersonResourceModel personGetPayload;
	
	Person person;
	
	@Mock
	ChangeStatusPersonModel changeStatusPersonModel;
		
	@Mock
	ChangeStatusPersonValidator changeStatusPersonValidator;
	
	@Before
	public void setUp(){
		personGetPayload =ReadObjectFromFile.getObjectFromJson("AddedStatusPerson.json", PersonResourceModel.class);
		person = ReadObjectFromFile.getObjectFromJson("InCheckStatusPerson.json", Person.class);
	}
	
	@Test(expected = RuntimeException.class)
	public void changeStatusForNullInput(){
		when(retrievePersonService.getPersonById(Mockito.anyInt())).thenReturn(personGetPayload);
		when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
		when(changeStatusPersonModel.getState()).thenReturn("INCHECK");
		PersonResourceModel personDataAfterChangeState = changeStatusPersonService.changeStatusPerson(1, null);
		
	}
}
