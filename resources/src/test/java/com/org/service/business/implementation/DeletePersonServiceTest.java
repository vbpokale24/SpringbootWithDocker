package com.org.service.business.implementation;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.repository.PersonRepository;
import com.org.service.business.implementation.DeletePersonService;

@RunWith(SpringRunner.class)
public class DeletePersonServiceTest {

	@InjectMocks
	private DeletePersonService deletePersonService;
	
	@Mock
	private PersonRepository personRepository;
	
	@Test
	public void testDeletePeoplyByID(){
		deletePersonService.deletePersonById(1);
		verify(personRepository, times(1)).deleteById(1);
	}
	
}
