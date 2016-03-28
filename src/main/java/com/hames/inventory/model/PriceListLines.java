package com.hames.inventory.model;

import java.math.BigDecimal;

public class PriceListLines {
	
	private String productId;
	private BigDecimal productPrice;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString() {
		return "PriceListLines [productId=" + productId + ", productPrice=" + productPrice + "]";
	}
	
	
}
