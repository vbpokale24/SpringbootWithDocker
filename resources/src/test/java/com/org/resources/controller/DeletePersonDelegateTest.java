package com.org.resources.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.interfaces.IDeletePersonService;

@RunWith(SpringRunner.class)
public class DeletePersonDelegateTest {

	@InjectMocks
	private DeletePersonDelegate deletePersonDelegate;
	
	@Mock
	private IDeletePersonService deletePersonService;
	
	@Test
	public void testDeletePeoplyByID(){
		assertEquals(deletePersonDelegate.deletePersonById(1).getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	
}
