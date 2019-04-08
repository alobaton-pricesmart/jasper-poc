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

import net.sf.jasperreports.engine.JRException;

/**
 * @author alobaton
 *
 */
@ControllerAdvice
public class JasperPocAdvice {

	@ExceptionHandler(JRException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<JasperPocError> registerNotFoundExceptionHandler(JRException ex) {
		JasperPocError error = new JasperPocError();
		error.setMessage(ex.getMessage());
		error.addError(ex.getCause().toString());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
