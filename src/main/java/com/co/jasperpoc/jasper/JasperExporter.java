/**
 * 
 */
package com.co.jasperpoc.jasper;

import java.io.IOException;
import java.io.OutputStream;

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
	 * @param content      The file content
	 * @param outputStream Output stream
	 * @throws IOException
	 * @throws JRException
	 */
	public void exportPDF(JasperPrint content, OutputStream outputStream) throws IOException, JRException {
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(content));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(Boolean.TRUE);
		reportConfig.setForceLineBreakPolicy(Boolean.FALSE);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setEncrypted(Boolean.TRUE);

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();
	}

}
