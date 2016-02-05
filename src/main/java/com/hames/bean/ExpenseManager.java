package com.hames.bean;

import org.joda.time.DateTime;

import com.hames.enums.ExpenseStatus;


public class ExpenseManager extends BaseBean {
	
	private String expenseId;
	private DateTime expenseDate;
	private String payeeName;
	private String notes;
	private Payment payment;
	private ExpenseCategory expenseCategory;
	private ExpenseStatus status;
	
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public DateTime getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(DateTime expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}
	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	public ExpenseStatus getStatus() {
		return status;
	}
	public void setStatus(ExpenseStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ExpenseManager [expenseId=" + expenseId + ", payeeName="
				+ payeeName + ", notes=" + notes + ", payment=" + payment
				+ ", expenseCategory=" + expenseCategory + "]";
	}
	
}
