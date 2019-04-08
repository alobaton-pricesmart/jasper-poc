/**
 * 
 */
package com.co.jasperpoc.advice;

import java.util.HashSet;
import java.util.Set;

/**
 * @author alobaton
 *
 */
public class JasperPocError {
	private String message;
	private Set<String> errors;

	public JasperPocError() {
		this.errors = new HashSet<>();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getErrors() {
		return errors;
	}

	public void setErrors(Set<String> errors) {
		this.errors = errors;
	}

	/**
	 * 
	 * @param error The error to add
	 */
	public void addError(String error) {
		this.errors.add(error);
	}

	@Override
	public String toString() {
		return "JasperPocError [message=" + message + "]";
	}

}
