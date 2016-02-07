package com.hames.util.model;

/**
 * Json Response
 * @author afilansari
 *
 */
public class JsonResponse {

	private Boolean status;
	private Object message;

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
	
	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", message=" + message + "]";
	}
	
}
