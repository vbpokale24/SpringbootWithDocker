package com.org.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.ICreatePersonDelegate;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.interfaces.IMessages;
import com.org.service.model.PersonResourceModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class CreatePersonDelegate implements ICreatePersonDelegate {

	private static Logger Logger = LoggerFactory.getLogger(CreatePersonDelegate.class);

	@Autowired
	private ICreatePersonService createCustomerService;

	@Autowired
	IMessages message;
	
	@Override
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<PersonResourceModel> addPerson(@RequestBody final PersonResourceModel person) {
		Logger.info("Creating the person data");
		if(person!=null){
			PersonResourceModel personData = createCustomerService.addPerson(person);
			return new ResponseEntity<PersonResourceModel>(personData, HttpStatus.CREATED);
		}else{
			throw new BadRequestException(BadRequestException.BAD_REQUEST_MSG, null, message, "");
		}
		
	}

}
