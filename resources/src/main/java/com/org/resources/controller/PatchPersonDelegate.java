package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.IPatchPersonDelegate;
import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.model.PersonResourceModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class PatchPersonDelegate implements IPatchPersonDelegate{

	@Autowired
	private IPatchPersonService patchPersonService;
	
	@Override 
	@RequestMapping( value = "/person/{personId}", method = RequestMethod.PATCH)
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<PersonResourceModel> patchPerson(@PathVariable(value="personId") final Integer personId, @RequestBody final PersonResourceModel persons) {
		PersonResourceModel personModel = patchPersonService.patchPerson(personId, persons);
		return new ResponseEntity<PersonResourceModel>(personModel,HttpStatus.OK);
	}
}
