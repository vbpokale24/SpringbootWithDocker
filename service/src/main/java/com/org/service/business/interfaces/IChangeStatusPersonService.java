package com.org.service.business.interfaces;

import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;

public interface IChangeStatusPersonService {

	public PersonResourceModel changeStatusPerson(final Integer person_id, final ChangeStatusPersonModel changeStatusPersonModel);
}
