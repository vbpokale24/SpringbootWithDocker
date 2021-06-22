package com.org.resources.controller.security.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.security.interfaces.IGetJWTToken;
import com.org.service.model.JWTTokenModel;
import com.org.utils.JWTUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/securitymanagement/v1")
public class GetJWTToken implements IGetJWTToken{

	private static Logger Logger = LoggerFactory.getLogger(GetJWTToken.class);
	
	@Value("${jwt.id}")
	private String jwtId;
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	
	@Value("${jwt.subject}")
	private String jwtSubject;
	
	@Value("${jwt.ttl}")
	private String ttl;
	@Override 
	@RequestMapping( value = "/getJwtToken", method = RequestMethod.GET)
	@ApiOperation(value = "", authorizations = { @Authorization(value="basicAuth") })
    public ResponseEntity<JWTTokenModel> getToken() {
		Logger.info("Generating security token");
		JWTTokenModel jwtTokenModel =  new JWTTokenModel();
		//Make Configurable
		jwtTokenModel.setSubjectToken(JWTUtils.createJWT(jwtId, jwtIssuer, jwtSubject, Long.valueOf(ttl)));
		return new ResponseEntity<JWTTokenModel>(jwtTokenModel,HttpStatus.CREATED);
	}
}
