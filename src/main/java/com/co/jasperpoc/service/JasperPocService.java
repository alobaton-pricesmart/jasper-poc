/**
 * 
 */
package com.co.jasperpoc.service;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;

/**
 * @author alobaton
 *
 */
@Service
public interface JasperPocService {

	public void hello(String name) throws JRException;

}
