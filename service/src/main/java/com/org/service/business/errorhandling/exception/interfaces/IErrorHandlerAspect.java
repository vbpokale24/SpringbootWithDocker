package com.org.service.business.errorhandling.exception.interfaces;

import org.aspectj.lang.JoinPoint;

public interface IErrorHandlerAspect {

	 public void handleErrors(JoinPoint joinPoint, Exception ex);
}
