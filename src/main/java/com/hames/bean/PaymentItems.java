package com.hames.bean;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.hames.enums.PaymentItemStatus;
import com.hames.enums.PaymentItemType;

public class PaymentItems {

	private DateTime paymentDate;
	private BigDecimal paymentAmount;
	private PaymentItemType paymentType;
	private PaymentItemStatus paymentItemStatus;
	private String description;
	
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public PaymentItemType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentItemType paymentType) {
		this.paymentType = paymentType;
	}
	public PaymentItemStatus getPaymentItemStatus() {
		return paymentItemStatus;
	}
	public void setPaymentItemStatus(PaymentItemStatus paymentItemStatus) {
		this.paymentItemStatus = paymentItemStatus;
	}
	public DateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "PaymentItems [paymentDate=" + paymentDate + ", paymentAmount="
				+ paymentAmount + ", paymentType=" + paymentType
				+ ", paymentItemStatus=" + paymentItemStatus + ", description="
				+ description + "]";
	}
	
}
