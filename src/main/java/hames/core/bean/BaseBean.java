package hames.core.bean;

import org.joda.time.DateTime;

public class BaseBean {

	private Integer staffCreated;
	private Integer staffModified;
	private DateTime dateCreated;
	private DateTime dateModified;

	public Integer getStaffCreated() {
		return staffCreated;
	}
	public void setStaffCreated(Integer staffCreated) {
		this.staffCreated = staffCreated;
	}
	public Integer getStaffModified() {
		return staffModified;
	}
	public void setStaffModified(Integer staffModified) {
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
	
	
	
}
