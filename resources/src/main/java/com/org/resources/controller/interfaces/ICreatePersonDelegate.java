package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.service.model.PersonResourceModel;

public interface ICreatePersonDelegate {
	
	public ResponseEntity<PersonResourceModel> addPerson(@RequestBody final PersonResourceModel persons);
	
}
