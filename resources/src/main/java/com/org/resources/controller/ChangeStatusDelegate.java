package com.org.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.IChangeStatusDelegate;
import com.org.service.business.interfaces.IChangeStatusPersonService;
import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class ChangeStatusDelegate implements IChangeStatusDelegate{

	private static Logger Logger = LoggerFactory.getLogger(ChangeStatusDelegate.class);
	
	@Autowired
	private IChangeStatusPersonService changeStatusPersonService;
	
	@Override
	@RequestMapping( value = "/person/{personId}", method = RequestMethod.POST)
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<PersonResourceModel> changeStatus(@PathVariable(value="personId") final Integer personId, @RequestBody final ChangeStatusPersonModel changeStatusPersonModel) {
		Logger.info("Change state API::");
		PersonResourceModel personResource = changeStatusPersonService.changeStatusPerson(personId, changeStatusPersonModel);
		return new ResponseEntity<PersonResourceModel>(personResource,HttpStatus.OK);
	}

}
