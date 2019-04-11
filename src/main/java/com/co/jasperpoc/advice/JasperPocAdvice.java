/**
 * 
 */
package com.co.jasperpoc.advice;

import java.io.IOException;

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
	public @ResponseBody ResponseEntity<JasperPocError> registerJREExceptionHandler(JRException ex) {
		JasperPocError error = new JasperPocError();
		error.setMessage(ex.getMessage());
		error.addError(ex.getCause().toString());

		ex.printStackTrace();

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<JasperPocError> registerIOExceptionHandler(IOException ex) {
		JasperPocError error = new JasperPocError();
		error.setMessage(ex.getMessage());
		error.addError(ex.getCause().toString());

		ex.printStackTrace();

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
