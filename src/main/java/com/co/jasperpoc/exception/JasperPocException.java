/**
 * 
 */
package com.co.jasperpoc.exception;

/**
 * @author alobaton
 *
 */
public class JasperPocException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5703241641711047922L;

	public JasperPocException(String message) {
		super(message);
	}
	
	public JasperPocException(String message, Throwable err) {
		super(message, err);
	}

}
