package com.hames.bean;

import org.joda.time.DateTime;

public class BaseBean {

	public String staffCreated;
	public String staffModified;
	public DateTime dateCreated;
	public DateTime dateModified;

	public String getStaffCreated() {
		return staffCreated;
	}
	public void setStaffCreated(String staffCreated) {
		this.staffCreated = staffCreated;
	}
	public String getStaffModified() {
		return staffModified;
	}
	public void setStaffModified(String staffModified) {
		this.staffModified = staffModified;
	}
	public DateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public DateTime getDateModified() {
		return dateModified;
	}
	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}
	
	public void setAuditableDetails(String id){
		if(id == null || id.isEmpty()){
			setStaffCreated(UserUtil.staff.getStaffId());
			setStaffModified(UserUtil.staff.getStaffId());
			setDateCreated(new DateTime());
			setDateModified(new DateTime());
		}else{
			setStaffModified(UserUtil.staff.getStaffId());
			setDateModified(new DateTime());
		}
	}
}
