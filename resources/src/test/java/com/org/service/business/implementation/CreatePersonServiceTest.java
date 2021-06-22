package com.org.service.business.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.business.utils.PopulateHref;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class CreatePersonServiceTest {

	@InjectMocks
	private CreatePersonService createPersonService;
	
	@Mock
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	PopulateHref populateHref;
	
	Person personRequestPayloadDB;
	
	PersonResourceModel personGetPayload;
	
	@Before
	public void setUp()
	{
		personRequestPayloadDB =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(PersonResourceModel.class), Mockito.any())).thenReturn(personRequestPayloadDB);
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonResourceModel.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(Person.class), Mockito.any())).thenReturn(personGetPayload);
	}
	
	@Test
	public void testCreatePersonService(){
		when(personRepository.save(Mockito.any())).thenReturn(personRequestPayloadDB);
		PersonResourceModel personModel = createPersonService.addPerson(personGetPayload);
		assertEquals("29", personModel.getAge());
	}
}
