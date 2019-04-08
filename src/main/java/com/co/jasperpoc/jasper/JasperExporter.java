/**
 * 
 */
package com.co.jasperpoc.jasper;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

/**
 * @author alobaton
 *
 */
@Component
public class JasperExporter {
	/**
	 * Export to PDF.
	 * 
	 * @param filename The filename
	 * @param content  The file content
	 */
	public void exportPDF(String filename, JasperPrint content) {
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(content));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(String.format("%s.pdf", filename)));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(Boolean.TRUE);
		reportConfig.setForceLineBreakPolicy(Boolean.FALSE);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setEncrypted(Boolean.TRUE);

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
