/**
 * 
 */
package com.co.jasperpoc.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.co.jasperpoc.exception.JasperPocException;

/**
 * @author alobaton
 *
 */
@ControllerAdvice
public class JasperPocAdvice {

	@ExceptionHandler(JasperPocException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<JasperPocError> registerExceptionHandler(JasperPocException ex) {
		JasperPocError error = new JasperPocError();
		error.setMessage(ex.getMessage());
		error.addError(ex.getCause().toString());

		ex.printStackTrace();

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
