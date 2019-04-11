/**
 * 
 */
package com.co.jasperpoc.service;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author alobaton
 *
 */
public interface JasperPocService {

	public JasperPrint hello(String name) throws JRException, IOException;

}
