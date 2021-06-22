package com.org.service.business.interfaces;

import com.org.service.model.PersonResourceModel;

public interface IPatchPersonService {

	public 	PersonResourceModel patchPerson(final Integer person_id, final PersonResourceModel persons);
}
