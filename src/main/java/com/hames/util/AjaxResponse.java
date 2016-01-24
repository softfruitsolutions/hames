package com.hames.util;

public class AjaxResponse {

	private boolean status;

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AjaxResponse [status=" + status + "]";
	}
	
}
