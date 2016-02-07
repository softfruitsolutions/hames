package com.hames.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.hames.enums.PaymentStatus;

public class Payment {
	
	private String paymentId;
	private DateTime paymentDate;
	private BigDecimal totalAmount;
	private BigDecimal amountPaid;
	private BigDecimal discountAmount;
	private BigDecimal balanceDue;
	private List<PaymentItems> paymentItems;
	private PaymentStatus paymentStatus;
	private String paymentNotes;
	
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public DateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentNotes() {
		return paymentNotes;
	}
	public void setPaymentNotes(String paymentNotes) {
		this.paymentNotes = paymentNotes;
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
	public List<PaymentItems> getPaymentItems() {
		return paymentItems;
	}
	public void setPaymentItems(List<PaymentItems> paymentItems) {
		this.paymentItems = paymentItems;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public void addPaymentItems(PaymentItems paymentItem){
		if(paymentItems == null){
			paymentItems = new ArrayList<PaymentItems>();
			paymentItems.add(paymentItem);
		}else{
			paymentItems.add(paymentItem);
		}
	}
	
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public String toString() {
		return "Payment [totalAmount=" + totalAmount + ", amountPaid="
				+ amountPaid + ", discountAmount=" + discountAmount
				+ ", balanceDue=" + balanceDue + ", paymentItems="
				+ paymentItems + ", paymentStatus=" + paymentStatus + "]";
	}
	
}
