package com.hames.inventory.model;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;

public class ProductGroup {

	@Id
	private String productGroupId;
	private String productGroupName;
	private String productGroupDescription;
	private Audit audit;
	
	public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}
	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public String getProductGroupDescription() {
		return productGroupDescription;
	}
	public void setProductGroupDescription(String productGroupDescription) {
		this.productGroupDescription = productGroupDescription;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit() {
		this.audit = new Audit().setAudit(this.productGroupId);
	}
	
	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productGroupId + ", productCategoryName="
				+ productGroupName + ", productCategoryDescription=" + productGroupDescription + ", audit="
				+ audit + "]";
	}	
}
