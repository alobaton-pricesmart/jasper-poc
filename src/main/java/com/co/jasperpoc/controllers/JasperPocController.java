/**
 * 
 */
package com.co.jasperpoc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.jasperpoc.service.JasperPocService;

import net.sf.jasperreports.engine.JRException;

/**
 * @author alobaton
 *
 */
@RestController
public class JasperPocController {

	@Autowired
	private JasperPocService service;

	@RequestMapping("/hello/{name}")
	public void hello(@PathVariable String name) throws JRException {
		service.hello(name);
	}
}
