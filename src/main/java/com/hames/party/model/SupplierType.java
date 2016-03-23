package com.hames.party.model;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;
import com.hames.party.enums.SupplierTypeStatus;

public class SupplierType {

	@Id
	private String typeId;
	private String typeName;
	private String typeDescription;
	private SupplierTypeStatus typeStatus;
	private Audit audit;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public SupplierTypeStatus getTypeStatus() {
		return typeStatus;
	}
	public void setTypeStatus(SupplierTypeStatus typeStatus) {
		this.typeStatus = typeStatus;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	public void setAudit() {
		this.audit = getAudit().setAudit(this.typeId);
	}
	
	@Override
	public String toString() {
		return "SupplierType [typeId=" + typeId + ", typeName=" + typeName + ", typeDescription=" + typeDescription
				+ ", audit=" + audit + "]";
	}
	
}
