package com.hames.util.model;

import java.util.Map;

/**
 * Json Response
 * @author afilansari
 *
 */
public class JsonResponse {

	private Boolean status;
	private Object message;
	
	private Map<String, Object> datas;

	public JsonResponse() {
	}
	
	public JsonResponse(Boolean status, Object message) {
		this.status = status;
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", message=" + message + ", datas=" + datas + "]";
	}
	
}
