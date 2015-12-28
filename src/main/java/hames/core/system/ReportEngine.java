package hames.core.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReportEngine {

	private static final Logger logger = LoggerFactory.getLogger(ReportEngine.class);
	
	private static final String REPORT_LOCATION = "/hames/report/";
	
	private static InputStream getInputStream(String jrxmlFileName){
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

	public static void buildReport(JRDataSource dataSource, String jrxmlFileName, Map<String, Object> parameters){
		
		/**
		 * Compile JRXML File
		 */
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(getInputStream(jrxmlFileName));
		} catch (JRException e) {
			logger.error("Error Compiling Jasper report JRXML file.");
			e.printStackTrace();
		}
		
		if(parameters == null || parameters.isEmpty()){
			parameters = new HashMap<String, Object>();
			parameters.put("Title", "Hello World");
		}
		
		/**
		 * Creating Jasper Print
		 */
		JasperPrint jasperPrint = null; 
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		} catch (JRException e) {
			logger.error("Error printing Jasper Report");
			e.printStackTrace();
		}
		
		JasperViewer.viewReport(jasperPrint);
		
	    logger.debug("Successful printing Jasper Report");

	}
	
	public static void main(String[] args) {
		JRDataSource source = new JREmptyDataSource();
		ReportEngine.buildReport(source, "staff.jrxml", null);
	}
}
