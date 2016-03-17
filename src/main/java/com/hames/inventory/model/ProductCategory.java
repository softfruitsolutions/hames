package com.hames.inventory.model;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;

public class ProductCategory {

	@Id
	private String productCategoryId;
	private String productCategoryName;
	private String productDescription;
	private Audit audit;
	
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit() {
		this.audit = new Audit().setAudit(this.productCategoryId);
	}
	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + ", productDescription=" + productDescription + ", audit=" + audit + "]";
	}	
}
