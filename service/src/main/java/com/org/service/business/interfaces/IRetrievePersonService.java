package com.org.service.business.interfaces;

import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

public interface IRetrievePersonService {

	public PersonResourceModel getPersonById( Integer id);
	
	public PersonModel getAllPerson();
}
