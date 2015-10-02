package hames.core.bean;

import java.util.Date;

public class BaseBean {

	private Integer staffCreated;
	private Integer staffModified;
	private Date dateCreated;
	private Date dateModified;

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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	
}
