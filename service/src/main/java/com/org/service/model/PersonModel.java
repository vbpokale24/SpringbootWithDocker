package com.org.service.model;

import java.util.ArrayList;
import java.util.List;

public class PersonModel {
	
	List<PersonResourceModel> person =new ArrayList<>();

	public List<PersonResourceModel> getPerson() {
		return person;
	}

	public void setPerson(List<PersonResourceModel> person) {
		this.person = person;
	}
	
	
}
