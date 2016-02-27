package com.hames.report;

/**
 * Report Period
 * @author afilansari
 */

public enum ReportPeriod {
	
	DAILY("Daily"),
	MONTHLY("Monthly"),
	YEARLY("Yearly");
	
	private String text;

	private ReportPeriod(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
}
