package com.hames.service;

import com.hames.enums.SaleOrderStatus;
import com.hames.report.ReportManager;

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
	public ReportManager generateSaleReport();
}
