package com.hames.service;

import com.hames.enums.SaleOrderStatus;
import com.hames.report.ReportRequest;
import com.hames.report.ReportResponse;

public interface SaleOrderService extends OrderService{

	/**
	 * Update Order Status
	 * @param orderId
	 */
	public void updateOrderStatus(String orderId,SaleOrderStatus saleOrderStatus);
	
	/**
	 * Get Next Job No
	 * @return
	 */
	public String getNextJobNo();
	
	/**
	 * Generate Sale Report.
	 * @return
	 */
	public ReportResponse generateSaleReport(ReportRequest reportRequest);
	
	/**
	 * Get Sale Order Count
	 * Exclude cancelled orders.
	 *  
	 * @return
	 */
	public Long getSaleOrderCount();
}
