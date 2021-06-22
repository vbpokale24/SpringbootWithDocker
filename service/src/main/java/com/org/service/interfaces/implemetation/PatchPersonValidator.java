package com.org.service.interfaces.implemetation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IMessages;
import com.org.service.interfaces.IPatchPersonValidator;
import com.org.service.model.PersonResourceModel;

@Component
public class PatchPersonValidator implements IPatchPersonValidator{
	
	@Autowired
	private IMessages messages;
	
	@Override
	public boolean supports(Class<?> clazz) {

		return PersonResourceModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonResourceModel person = (PersonResourceModel) target;
		if(person==null){
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST,null, messages, "");
		}
	}
	
}
