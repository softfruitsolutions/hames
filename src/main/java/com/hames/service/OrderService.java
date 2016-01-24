package com.hames.service;

import com.hames.bean.Order;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

public interface OrderService {

	/**
	 * Save Order
	 * @param order
	 */
	public void saveOrder(Order order);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
}
