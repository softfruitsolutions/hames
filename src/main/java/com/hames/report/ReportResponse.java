package com.hames.report;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Transient;

public class ReportResponse {
	
	//Contains any specific values related to datas. It can be
	//either summed values or generic values;
	@Transient private Map<String,Object> values;
	@Transient private List<Map<String,Object>> datas;
	
	public ReportResponse(Map<String, Object> values, List<Map<String, Object>> datas) {
		this.values = values;
		this.datas = datas;
	}
	
	public Map<String, Object> getValues() {
		return values;
	}
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	public List<Map<String, Object>> getDatas() {
		return datas;
	}
	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "ReportManager [values=" + values + ", datas=" + datas + "]";
	}
	
}
