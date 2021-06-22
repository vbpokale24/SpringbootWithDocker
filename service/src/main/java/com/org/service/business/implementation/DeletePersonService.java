package com.org.service.business.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.persistence.repository.PersonRepository;
import com.org.service.business.interfaces.IDeletePersonService;

@Service
public class DeletePersonService implements IDeletePersonService{

	@Autowired
	PersonRepository personRepository;

	@Override
	public void deletePersonById(Integer person_id) {
		personRepository.deleteById(person_id);
	}
	
}
