package hames.core.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportEngine {

	private static final Logger logger = LoggerFactory.getLogger(ReportEngine.class);

	public static void buildReport(JRDataSource dataSource, String jrxmlFile, Map<String, Object> parameters){
		
		/**
		 * Compile JRXML File
		 */
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(jrxmlFile);
		} catch (JRException e) {
			logger.error("Error Compiling Jasper report JRXML file.");
			e.printStackTrace();
		}
		
		if(parameters == null || parameters.isEmpty()){
			parameters = new HashMap<String, Object>();
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
		
		//Make sure the output directory exists.
	    File outDir = new File("C:/jasperoutput");
	    outDir.mkdirs();
	 
	    // Export to PDF.
	    try {
			JasperExportManager.exportReportToPdfFile(jasperPrint,"C:/jasperoutput/StyledTextReport.pdf");
		} catch (JRException e) {
			logger.error("Error exporting jasper report.");
			e.printStackTrace();
		}
	        
	    logger.debug("Successful Printing Jasper Report");

	}
}
