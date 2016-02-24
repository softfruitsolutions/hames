package com.hames.dao;

import com.hames.bean.SaleOrder;
import com.hames.report.ReportManager;
import com.mongodb.AggregationOutput;

public interface SaleOrderDao extends OrderDao{

	/**
	 * Is Sale Order Job No Exists
	 * @param jobNo
	 * @return
	 */
	public boolean isJobNoExists(String jobNo);
	
	/**
	 * Find a Order
	 * @param orderId
	 * @return
	 */
	public SaleOrder findByOrderId(String orderId);
	
	/**
	 * Get Report Manager based on criteria
	 * @return
	 */
	public AggregationOutput getSaleReport();
	
}
