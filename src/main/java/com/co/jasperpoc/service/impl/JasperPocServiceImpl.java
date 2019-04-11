/**
 * 
 */
package com.co.jasperpoc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
@Service
public class JasperPocServiceImpl implements JasperPocService {

	private static final String TITLE = "TITLE";
	private static final String NAME = "NAME";

	@Value("${reports.hello.file-name}")
	private String fileName;

	@Value("${reports.hello.title}")
	private String title;

	@Override
	public JasperPrint hello(String name) throws JRException, IOException {
		InputStream stream = getClass().getResourceAsStream(String.format("%s.jrxml", fileName));
		JasperReport report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(TITLE, title);
		parameters.put(NAME, name);

		return JasperFillManager.fillReport(report, parameters);
	}

}
