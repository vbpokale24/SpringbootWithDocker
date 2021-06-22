package com.org.resources.controller.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.IChangeStatusPersonService;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChangeStatusDelegateIntegrationTest {

	@Autowired
	private IChangeStatusPersonService changeStatusPersonService;
	
	@MockBean
    private PersonRepository personRepository;
	
    ChangeStatusPersonModel changeStatusPersonModel;
	
	PersonResourceModel personGetPayload;
	
	Person personRequestPayloadDBSave;
	
	Person personRequestPayloadDB;
	
	@Before
	public void setUp(){
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonResourceModel.class);
		personRequestPayloadDB =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		personRequestPayloadDBSave =ReadObjectFromFile.getObjectFromJson("InCheckStatusPerson.json", Person.class);
	}
	
	@Test
	public void testChangeDelegateToInCheck(){
		changeStatusPersonModel = new ChangeStatusPersonModel();
		changeStatusPersonModel.setState("INCHECK");
		when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(personRequestPayloadDB));
		when(personRepository.save(Mockito.any())).thenReturn(personRequestPayloadDBSave);
		PersonResourceModel  person = changeStatusPersonService.changeStatusPerson(1,changeStatusPersonModel);
		assertEquals(person.getState(),"INCHECK");
	}
	
	@Test(expected=BadRequestException.class)
	public void testChangeDelegateToWrongState(){
		changeStatusPersonModel = new ChangeStatusPersonModel();
		changeStatusPersonModel.setState("ACTIVE");
		when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(personRequestPayloadDB));
		when(personRepository.save(Mockito.any())).thenReturn(personRequestPayloadDBSave);
		PersonResourceModel  person = changeStatusPersonService.changeStatusPerson(1,changeStatusPersonModel);
	}
}
