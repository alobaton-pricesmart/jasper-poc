/**
 * 
 */
package com.co.jasperpoc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.jasperpoc.jasper.JasperExporter;
import com.co.jasperpoc.service.JasperPocService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author alobaton
 *
 */
@RestController
public class JasperPocController {

	@Autowired
	private JasperPocService service;

	@Autowired
	private JasperExporter exporter;

	@RequestMapping("/hello/{name}")
	public void hello(@PathVariable String name, HttpServletResponse response) throws JRException, IOException {
		JasperPrint content = service.hello(name);

		exporter.exportPDF(content, response.getOutputStream());
	}
}
