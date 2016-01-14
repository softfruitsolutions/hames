package com.hames.bean;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Order extends BaseBean{

	@Id
	private String orderId;
	private String partyId;
	private DateTime deliveryDate;
	private DateTime orderDate;
	
	/**
	 * Total Amount
	 * @return
	 */
	//private List<Payment> payments;
	private BigDecimal totalAmount;
	
	/**
	 * Transient Fields
	 */
	@Transient
	private BigDecimal balanceDue;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public DateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(DateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public DateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(DateTime orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}
	
}
