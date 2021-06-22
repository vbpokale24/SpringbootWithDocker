package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

public interface IRetrievePersonDelegate {

	public ResponseEntity<PersonResourceModel> getPersonById(Integer id);

	public ResponseEntity<PersonModel> getAllPerson();

}
