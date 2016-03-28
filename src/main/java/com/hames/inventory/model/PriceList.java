package com.hames.inventory.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;
import com.hames.inventory.enums.PriceListStatus;

public class PriceList {

	@Id
	private String priceListId;
	private String priceListName;
	private String priceListDescription;
	private Boolean isDefault;
	private List<PriceListLines> priceListLines;
	private PriceListStatus status;
	private Audit audit;
	
	public String getPriceListId() {
		return priceListId;
	}
	public void setPriceListId(String priceListId) {
		this.priceListId = priceListId;
	}
	public String getPriceListName() {
		return priceListName;
	}
	public void setPriceListName(String priceListName) {
		this.priceListName = priceListName;
	}
	public String getPriceListDescription() {
		return priceListDescription;
	}
	public void setPriceListDescription(String priceListDescription) {
		this.priceListDescription = priceListDescription;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public List<PriceListLines> getPriceListLines() {
		return priceListLines;
	}
	public void setPriceListLines(List<PriceListLines> priceListLines) {
		this.priceListLines = priceListLines;
	}
	public PriceListStatus getStatus() {
		return status;
	}
	public void setStatus(PriceListStatus status) {
		this.status = status;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit() {
		this.audit = getAudit().setAudit(this.priceListId);
	}
	
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	@Override
	public String toString() {
		return "PriceList [priceListId=" + priceListId + ", priceListName=" + priceListName + ", priceListDescription="
				+ priceListDescription + ", isDefault=" + isDefault + ", priceListLines=" + priceListLines + ", status="
				+ status + "]";
	}
	
}
