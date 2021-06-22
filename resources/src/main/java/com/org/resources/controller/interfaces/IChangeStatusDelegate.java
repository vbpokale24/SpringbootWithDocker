package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.ChangeStatusPersonModel;
import com.org.service.model.PersonResourceModel;

public interface IChangeStatusDelegate {

	public ResponseEntity<PersonResourceModel> changeStatus(Integer id, final ChangeStatusPersonModel changeStatusModel);
}
