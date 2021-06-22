package com.org.service.business.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.business.utils.PopulateHref;
import com.org.service.model.PersonResourceModel;
import com.org.service.model.PersonState;

@Service
public class CreatePersonService implements ICreatePersonService{

	private static Logger Logger = LoggerFactory.getLogger(CreatePersonService.class);
	
	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PopulateHref populateHref;
	
	
	
	@Override
	public PersonResourceModel addPerson(PersonResourceModel person) {
		person.setState(PersonState.ADDED.toString());
		Person personDTO = mapSourceObjectToTargetObject.mapResource(person, Person.class);
		Person personResult = personRepository.save(personDTO);
		Logger.info("Person data saved successfully");
		PersonResourceModel personresource  = mapSourceObjectToTargetObject.mapResource(personResult, PersonResourceModel.class);
		populateHref.buildHref(personresource);
		return personresource;
	}

}
