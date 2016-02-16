package com.hames.bean;

import com.hames.enums.SaleOrderStatus;

public class SaleOrder extends Order{

	public String jobNo;
	private String jobName;
	private String jobDescription;
	private String paper;
	private String proof;
	private String film;
	private String colors;
	private String screenPrinting;
	private String plate;
	private String printingPaper;
	private String printingSize;
	private String printingQuantity;
	private String printingInk;
	private String printingColors;
	private String quantity;
	private String printing;
	private String numbering;
	private String stiching;
	private String binding;
	private String other;
	private String remarks;
	private SaleOrderStatus saleOrderStatus;
	
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public String getFilm() {
		return film;
	}
	public void setFilm(String film) {
		this.film = film;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getScreenPrinting() {
		return screenPrinting;
	}
	public void setScreenPrinting(String screenPrinting) {
		this.screenPrinting = screenPrinting;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getPrintingPaper() {
		return printingPaper;
	}
	public void setPrintingPaper(String printingPaper) {
		this.printingPaper = printingPaper;
	}
	public String getPrintingSize() {
		return printingSize;
	}
	public void setPrintingSize(String printingSize) {
		this.printingSize = printingSize;
	}
	public String getPrintingQuantity() {
		return printingQuantity;
	}
	public void setPrintingQuantity(String printingQuantity) {
		this.printingQuantity = printingQuantity;
	}
	public String getPrintingInk() {
		return printingInk;
	}
	public void setPrintingInk(String printingInk) {
		this.printingInk = printingInk;
	}
	public String getPrintingColors() {
		return printingColors;
	}
	public void setPrintingColors(String printingColors) {
		this.printingColors = printingColors;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrinting() {
		return printing;
	}
	public void setPrinting(String printing) {
		this.printing = printing;
	}
	public String getNumbering() {
		return numbering;
	}
	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}
	public String getStiching() {
		return stiching;
	}
	public void setStiching(String stiching) {
		this.stiching = stiching;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public SaleOrderStatus getSaleOrderStatus() {
		return saleOrderStatus;
	}
	public void setSaleOrderStatus(SaleOrderStatus saleOrderStatus) {
		this.saleOrderStatus = saleOrderStatus;
	}
	
	@Override
	public String toString() {
		return "SaleOrder [jobNo=" + jobNo + ", jobName=" + jobName
				+ ", jobDescription=" + jobDescription + ", paper=" + paper
				+ ", proof=" + proof + ", film=" + film + ", colors=" + colors
				+ ", screenPrinting=" + screenPrinting + ", plate=" + plate
				+ ", printingPaper=" + printingPaper + ", printingSize="
				+ printingSize + ", printingQuantity=" + printingQuantity
				+ ", printingInk=" + printingInk + ", printingColors="
				+ printingColors + ", quantity=" + quantity + ", printing="
				+ printing + ", numbering=" + numbering + ", stiching="
				+ stiching + ", binding=" + binding + ", other=" + other
				+ ", remarks=" + remarks + ", saleOrderStatus="
				+ saleOrderStatus + "]";
	}

}
