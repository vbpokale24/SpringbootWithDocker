package com.org.service.business.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.interfaces.IMessages;
import com.org.service.interfaces.IPatchPersonValidator;
import com.org.service.model.PersonResourceModel;

@Service
public class PatchPersonService implements IPatchPersonService {

	@Autowired
	private IRetrievePersonService retrievePersonService;

	@Autowired
	private IPatchPersonValidator patchPersonValidator;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;

	@Autowired
	private IMessages messages;

	@Override
	public PersonResourceModel patchPerson(final Integer personId, final PersonResourceModel person) {
		BindingResult result = new BeanPropertyBindingResult(person, "person");
		ValidationUtils.invokeValidator(patchPersonValidator, person, result);
		PersonResourceModel personModel = retrievePersonService.getPersonById(personId);
		if (personModel != null) {
			if(!personModel.getState().equals(person.getState())){
				throw new BadRequestException(BadRequestException.INVALID_STATE_CHANGE_STATUS, null, messages, "");
			}
			person.setId(personId);
			Person patchPersonModel = mapSourceObjectToTargetObject.mapResource(person, Person.class);
			Person personResult = personRepository.save(patchPersonModel);			
			return mapSourceObjectToTargetObject.mapResource(personResult, PersonResourceModel.class);
		} else {
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST, null, messages, "");
		}

	}

}
