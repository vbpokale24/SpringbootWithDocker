package com.org.resources.controller.security.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.JWTTokenModel;

public interface IGetJWTToken {

	ResponseEntity<JWTTokenModel> getToken();
}
