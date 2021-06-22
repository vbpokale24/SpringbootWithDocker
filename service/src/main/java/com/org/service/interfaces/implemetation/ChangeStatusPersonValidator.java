package com.org.service.interfaces.implemetation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IChangeStatusPersonValidator;
import com.org.service.interfaces.IMessages;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonState;

@Component
public class ChangeStatusPersonValidator implements IChangeStatusPersonValidator{

	private static Logger Logger = LoggerFactory.getLogger(ChangeStatusPersonValidator.class);
	
	@Autowired
	private IMessages messages;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangeStatusPersonModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangeStatusPersonModel person = (ChangeStatusPersonModel) target;
		if(person ==null || person.getState()==null){
			Logger.info("State in request is null");
			throw new BadRequestException(BadRequestException.INVALID_STATE_CHANGE_STATUS,null, messages, "");
		}
		if(person.getState().equals(PersonState.ADDED.toString())){
			Logger.info("Illegal state ADDED in request");
			throw new BadRequestException(BadRequestException.INVALID_STATE_CHANGE_STATUS,null, messages, "");
		}
		
	}

}
