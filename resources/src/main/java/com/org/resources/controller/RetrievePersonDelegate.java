package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.IRetrievePersonDelegate;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@RestController
@RequestMapping(value = "/personmanagement/v1")
public class RetrievePersonDelegate implements IRetrievePersonDelegate{

	@Autowired
	private IRetrievePersonService retrievePersonService;
	
	@Override 
	@RequestMapping( value = "/person/{personId}", method = RequestMethod.GET)
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<PersonResourceModel> getPersonById(@PathVariable(value="personId") final Integer personId) {
		return new ResponseEntity<PersonResourceModel>(retrievePersonService.getPersonById(personId), HttpStatus.OK);
	}
	
	@Override 
	@RequestMapping( value = "/person", method = RequestMethod.GET)
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<PersonModel> getAllPerson() {
		return new ResponseEntity<PersonModel>(retrievePersonService.getAllPerson(), HttpStatus.OK);
	}
}
