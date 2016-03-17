package com.hames.bean;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hames.service.StaffService;

@Component
public class Audit {

	@Autowired StaffService staffService;
	
	public String staffCreated;
	public String staffModified;
	public DateTime dateCreated;
	public DateTime dateModified;
	
	private String createdByText;
	private String lastModifiedByText;

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
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}
	public String getLastModifiedByText() {
		return lastModifiedByText;
	}
	public void setLastModifiedByText(String lastModifiedByText) {
		this.lastModifiedByText = lastModifiedByText;
	}
	
	public Audit setAudit(String id){
		if(id == null || id.isEmpty()){
			this.staffCreated = UserContext.staff.getStaffId();
			this.staffModified = UserContext.staff.getStaffId();
			this.createdByText = UserContext.staff.getFullName();
			this.lastModifiedByText = UserContext.staff.getFullName();
			
			this.dateCreated = new DateTime();
			this.dateModified = new DateTime();
		}else{
			this.staffModified = UserContext.staff.getStaffId();
			this.lastModifiedByText = UserContext.staff.getFullName();
			
			this.dateModified = new DateTime();
		}
		return this;
	}
	
}
