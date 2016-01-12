package com.hames.util;

import java.util.List;

public class DatatableResponse {
	
	public Long iTotalRecords;
	public Long iTotalDisplayRecords;
	public String sEcho;
	public List<?> aaData;

	public DatatableResponse() {
	}
	
	public DatatableResponse(String sEcho) {
		this.sEcho = sEcho;
	}
	
	public Long getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public List<?> getAaData() {
		return aaData;
	}
	public void setAaData(List<?> aaData) {
		this.aaData = aaData;
	}
	
	@Override
	public String toString() {
		return "DatatableResponse [iTotalRecords=" + iTotalRecords
				+ ", iTotalDisplayRecords=" + iTotalDisplayRecords + ", sEcho="
				+ sEcho + "]";
	}

	
}
