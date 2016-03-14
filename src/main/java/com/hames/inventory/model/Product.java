package com.hames.inventory.model;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;
import com.hames.inventory.enums.ProductType;
import com.hames.inventory.enums.UnitOfMeasure;

public class Product{
	
	@Id
	private String productId;
	private String productCode;
	private String productName;
	private String productCategory;
	private ProductType productType;
	private String productDescription;
	private UnitOfMeasure uom;
	private Audit audit;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public UnitOfMeasure getUom() {
		return uom;
	}
	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productCode=" + productCode + ", productName=" + productName
				+ ", productType=" + productType + ", productDescription=" + productDescription + ", uom=" + uom + "]";
	}

}
