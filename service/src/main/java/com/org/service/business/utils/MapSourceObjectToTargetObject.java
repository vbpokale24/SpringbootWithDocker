package com.org.service.business.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MapSourceObjectToTargetObject {

	@Autowired
	private ObjectMapper objectMapper;

	public <T> T mapResource(Object resource, Class<T> clazz) {
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(resource), clazz);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
