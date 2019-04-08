/**
 * 
 */
package com.co.jasperpoc.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.jasperpoc.jasper.JasperExporter;
import com.co.jasperpoc.service.JasperPocService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author alobaton
 *
 */
public class JasperPocServiceImpl implements JasperPocService {

	private static final String FILENAME = "hello-world-report";

	@Autowired
	private JasperExporter exporter;

	@Override
	public void hello(String name) throws JRException {
		InputStream stream = getClass().getResourceAsStream(String.format("%s.jrxml", FILENAME));
		JasperReport report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Hello World Report");
		parameters.put("name", name);

		JasperPrint content = JasperFillManager.fillReport(report, parameters);

		exporter.exportPDF(FILENAME, content);
	}

}
