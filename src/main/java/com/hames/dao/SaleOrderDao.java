package com.hames.dao;

import com.hames.bean.SaleOrder;

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
	SaleOrder findByOrderId(String orderId);
	
}
