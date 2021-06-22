package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.PersonResourceModel;

public interface IPatchPersonDelegate {

	public ResponseEntity<PersonResourceModel> patchPerson(final Integer person_id, final PersonResourceModel persons);
}
