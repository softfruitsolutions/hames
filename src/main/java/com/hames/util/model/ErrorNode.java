package com.hames.util.model;

import com.hames.util.enums.ErrorCode;

/**
 * Error - Error representation
 * @author Afil Ansari
 *
 */
public class ErrorNode {

	public static final String UNAUTHORIZED_ACCESS_MESSAGE = "You aren't authorized to access this. This incident will be reported";
	
	private ErrorCode errorCode;
	private String errorSubCode;
	private String errorDescription;
	
	//To handle the unauthorized access
	private String redirectUrl;
	
	public ErrorNode(ErrorCode errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	
	public ErrorNode(ErrorCode errorCode, String errorSubCode,
			String errorDescription) {
		this.errorCode = errorCode;
		this.errorSubCode = errorSubCode;
		this.errorDescription = errorDescription;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getErrorSubCode() {
		return errorSubCode;
	}
	public void setErrorSubCode(String errorSubCode) {
		this.errorSubCode = errorSubCode;
	}

	@Override
	public String toString() {
		return "ErrorNode [errorCode=" + errorCode + ", errorSubCode="
				+ errorSubCode + ", errorDescription=" + errorDescription
				+ ", redirectUrl=" + redirectUrl + "]";
	}
	
	
}
