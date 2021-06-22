package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.persistence.model.Person;

public interface IDeletePersonDelegate {

	public ResponseEntity<Person> deletePersonById(final Integer person_id) ;
}
