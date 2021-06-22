package com.org.resources.controller.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreatePersonDelegateIntegrationTest {

	@Autowired
	private ICreatePersonService createCustomerService;
	
	@MockBean
    private PersonRepository personRepository;
	
	Person personRequestPayloadDB;
	
	@Test
	public void createPeopleData(){
		personRequestPayloadDB =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		PersonResourceModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json", PersonResourceModel.class);
		when(personRepository.save(Mockito.any())).thenReturn(personRequestPayloadDB);
		PersonResourceModel result = createCustomerService.addPerson(personRequestPayload);
		assertEquals("Vaibhav", result.getFirstName());
		assertNotNull(result);
	}	
	
}
