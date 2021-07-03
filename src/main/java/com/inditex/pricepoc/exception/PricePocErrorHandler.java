package com.inditex.pricepoc.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom price API error handling mechanism.
 * @author Debora RT
 *
 */
@ControllerAdvice
public class PricePocErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getCause().toString();
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			MethodArgumentTypeMismatchException exNv = (MethodArgumentTypeMismatchException) ex;
			message = exNv.getName() + ": " + exNv.getCause();
		}
		return new ResponseEntity<Object>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status,
			org.springframework.web.context.request.WebRequest request) {
		return new ResponseEntity<Object>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

}
