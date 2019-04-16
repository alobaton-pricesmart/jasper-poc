/**
 * 
 */
package com.co.jasperpoc.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.co.jasperpoc.exception.JasperPocException;
import com.co.jasperpoc.service.JasperPocService;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
	public JasperPrint hello(String name) {
		InputStream stream = getClass().getResourceAsStream(String.format("%s.jrxml", fileName));
		JasperReport report = null;
		try {
			report = JasperCompileManager.compileReport(stream);
		} catch (JRException e) {
			throw new JasperPocException(e.getMessage(), e);
		}

		if (report == null) {
			throw new JasperPocException("report can't be null");
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(TITLE, title);
		parameters.put(NAME, name);

		// JasperFillmanager.fillReport need a datasource to work correctly if at
		// designer was specified.
		try {
			return JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		} catch (JRException e) {
			throw new JasperPocException(e.getMessage(), e);
		}
	}

	@Override
	public JasperPrint dynamic(String name) {
		FastReportBuilder builder = new FastReportBuilder();

		try {
			builder.addAutoText("$P{TITLE}",  AutoText.POSITION_HEADER, AutoText.ALIGMENT_CENTER);
			builder.addAutoText("$P{NAME}", AutoText.POSITION_HEADER, AutoText.ALIGMENT_CENTER);
			builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT);
			builder.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT,
					AutoText.PATTERN_DATE_DATE_TIME);
			builder.setUseFullPageWidth(Boolean.TRUE);
		} catch (ColumnBuilderException e) {
			throw new JasperPocException(e.getMessage(), e);
		}

		DynamicReport dynamicReport = builder.build();

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(TITLE, title);
		parameters.put(NAME, name);

		JasperReport report;
		try {
			report = DynamicJasperHelper.generateJasperReport(dynamicReport, new ClassicLayoutManager(), parameters);
		} catch (JRException e) {
			throw new JasperPocException(e.getMessage(), e);
		}
		
		if (report == null) {
			throw new JasperPocException("report can't be null");
		}

		try {
			return JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		} catch (JRException e) {
			throw new JasperPocException(e.getMessage(), e);
		}
	}

}
