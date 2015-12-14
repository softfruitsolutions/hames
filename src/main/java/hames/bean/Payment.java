package hames.bean;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import hames.core.bean.BaseBean;

public class Payment extends BaseBean{
	
	private Long paymentId;
	private Long orderId;
	private BigDecimal paymentAmount;
	private DateTime paymentDate;
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public DateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderId=" + orderId
				+ ", paymentAmount=" + paymentAmount + ", paymentDate="
				+ paymentDate + "]";
	}
	
	

}
