package com.org.service.business.errorhandling.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception exception) {
		
		BadGatewayException badGateWayException = null;
		BadRequestException badRequestException = null;
		ErrorResponse errorResponse = new ErrorResponse();
		//check exception and set the appropiate httpStatuscode
		if (exception instanceof BadGatewayException) {
			badGateWayException = (BadGatewayException) exception;
			errorResponse.setCode(badGateWayException.getParameters().get("ERROR_CODE").toString());
			errorResponse.setMessage(badGateWayException.getMessage());
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(errorResponse, status);
		}
		if (exception instanceof BadRequestException) {
			
			badRequestException = (BadRequestException) exception;
			errorResponse.setCode(badRequestException.getParameters().get("ERROR_CODE").toString());
			errorResponse.setMessage(badRequestException.getMessage());
			HttpStatus status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(errorResponse, status);
		}
		
       if (exception instanceof Exception) {
			errorResponse.setCode("500");
			errorResponse.setMessage(exception.getMessage());
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(errorResponse, status);
		}
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
