package com.hames.util.model;

import com.hames.util.enums.SuccessCode;

/**
 * Success - Json Response Util Class
 * @author afilansari
 *
 */
public class SuccessNode {

	private SuccessCode successCode;
	private String successDescription;
	
	private String redirectUrl;
	
	public SuccessNode(SuccessCode successCode, String successDescription) {
		this.successCode = successCode;
		this.successDescription = successDescription;
	}
	
	public SuccessCode getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(SuccessCode successCode) {
		this.successCode = successCode;
	}
	public String getSuccessDescription() {
		return successDescription;
	}
	public void setSuccessDescription(String successDescription) {
		this.successDescription = successDescription;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return "Success [successCode=" + successCode + ", successDescription="
				+ successDescription + "]";
	}
	
}
