/*package com.hames.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReportEngine {

	private static final Logger logger = LoggerFactory.getLogger(ReportEngine.class);
	
	private static final String REPORT_LOCATION = "/hames/report/";
	
	private InputStream getInputStream(String jrxmlFileName){
		InputStream inputStream = null;
		
		try{
			Resource resource = new ClassPathResource(REPORT_LOCATION+jrxmlFileName);
			inputStream = resource.getInputStream();
		}catch(IOException e) {
			logger.debug("File not found in location : {}",REPORT_LOCATION+jrxmlFileName);
			e.printStackTrace();
		}
		
		return inputStream;
	}

	public byte[] buildReport(JRDataSource dataSource, String jrxmlFileName, Map<String, Object> parameters){
		
		*//**
		 * Compile JRXML File
		 *//*
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(getInputStream(jrxmlFileName));
		} catch (JRException e) {
			logger.error("Error Compiling Jasper report JRXML file.");
			e.printStackTrace();
		}
		
		if(parameters == null || parameters.isEmpty()){
			parameters = new HashMap<String, Object>();
		}
		
		*//**
		 * Creating Jasper Print
		 *//*
		JasperPrint jasperPrint = null; 
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		} catch (JRException e) {
			logger.error("Error printing Jasper Report");
			e.printStackTrace();
		}
		
		byte[] report = null;
		try {
			 report = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			logger.error("Error building report to bytes format. Compilation Error");
			e.printStackTrace();
		}
		
		return report;
	}
	
	public byte[] renderReport(HttpServletResponse response, JRDataSource dataSource, String jrxmlFileName, Map<String, Object> parameters){
		byte[] content = buildReport(dataSource, jrxmlFileName, parameters);
	    try {
	    	response.setContentType("application/pdf");
		    response.setContentLength(content.length);
		    response.setHeader("Content-Disposition",  "inline;filename=report.pdf");
			response.getOutputStream().write(content, 0, content.length);
		} catch (IOException e) {
			logger.debug("Error writing content to response OutputStream.");
			e.printStackTrace();
		}
	    
	    return content;
	}

}
*/