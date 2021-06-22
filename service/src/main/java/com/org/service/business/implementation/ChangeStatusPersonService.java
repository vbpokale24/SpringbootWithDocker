package com.org.service.business.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.IChangeStatusPersonService;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.interfaces.IChangeStatusPersonValidator;
import com.org.service.interfaces.IMessages;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.service.model.PersonState;

@Service
public class ChangeStatusPersonService implements IChangeStatusPersonService {

	private static Logger Logger = LoggerFactory.getLogger(ChangeStatusPersonService.class);

	@Autowired
	IChangeStatusPersonValidator changeStatusPersonValidator;

	@Autowired
	private IRetrievePersonService retrievePersonService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;

	@Autowired
	private IMessages messages;

	@Override
	public PersonResourceModel changeStatusPerson(Integer personId, ChangeStatusPersonModel changeStatusPersonModel) {
		BindingResult result = new BeanPropertyBindingResult(changeStatusPersonModel, "changeStatusPersonModel");
		ValidationUtils.invokeValidator(changeStatusPersonValidator, changeStatusPersonModel, result);
		PersonResourceModel patchResourceModel = retrievePersonService.getPersonById(personId);
		if (patchResourceModel != null) {
			Logger.info("Current state::{}",patchResourceModel.getState());
			if ((PersonState.next(PersonState.valueOf(patchResourceModel.getState())))
					.equals(PersonState.valueOf(changeStatusPersonModel.getState()))) {
				patchResourceModel.setState(changeStatusPersonModel.getState());
			} else {
				throw new BadRequestException(BadRequestException.INVALID_STATE_CHANGE_STATUS, null, messages, "");
			}
			Person patchPersonModel = mapSourceObjectToTargetObject.mapResource(patchResourceModel, Person.class);
			Person personResult = personRepository.save(patchPersonModel);
			return mapSourceObjectToTargetObject.mapResource(personResult, PersonResourceModel.class);
		} else {
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST, null, messages, "");
		}
	}

}
