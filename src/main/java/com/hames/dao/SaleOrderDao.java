package com.hames.dao;

import java.util.List;
import java.util.Map;

import com.hames.bean.SaleOrder;
import com.hames.order.model.SaleOrderReport;

public interface SaleOrderDao extends OrderDao<SaleOrder>{

	/**
	 * Is Sale Order Job No Exists
	 * @param jobNo
	 * @return
	 */
	public boolean isJobNoExists(String jobNo);
	
	/**
	 * Get Report Response based on criteria
	 * @return
	 */
	public List<Map<String,Object>> getSaleReport(SaleOrderReport saleOrderReport);
	
	/**
	 * Find Sale Order Count
	 * @return
	 */
	public Long findSaleOrderCount();
}
