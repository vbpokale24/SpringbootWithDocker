package com.org.service.business.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.interfaces.IMessages;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

@Service
public class RetrievePersonService implements IRetrievePersonService{

	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	private IMessages messages;
	
	@Override
	public PersonResourceModel getPersonById(Integer id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()){
			return mapSourceObjectToTargetObject.mapResource(person.get(), PersonResourceModel.class);
		}else{
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT,null, messages, "");
		}
		
	}

	@Override
	public PersonModel getAllPerson() {
		Iterable<Person> person = personRepository.findAll();
		PersonModel personModel = new PersonModel();
		List<PersonResourceModel> allPersons = new ArrayList<>(); 
		for (Person t : person) 
			allPersons.add(mapSourceObjectToTargetObject.mapResource(t, PersonResourceModel.class));
		personModel.setPerson(allPersons);
		return personModel;
		
	}

}
