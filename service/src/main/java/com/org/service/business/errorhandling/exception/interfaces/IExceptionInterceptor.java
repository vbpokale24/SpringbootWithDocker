package com.org.service.business.errorhandling.exception.interfaces;

import org.aspectj.lang.JoinPoint;
import org.springframework.http.ResponseEntity;

import com.org.service.business.errorhandling.exception.ErrorResponse;

public interface IExceptionInterceptor {
	public ResponseEntity<ErrorResponse> handleErrors(JoinPoint joinPoint, Throwable ex);
}
