package com.hames.service;

import com.hames.bean.Order;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface OrderService {

	/**
	 * Save Order
	 * @param order
	 */
	public void saveOrder(Order order);

	/**
	 * Get Order By Id
	 * @param orderId
	 * @return
	 */
	public <T> T getOrderById(String orderId);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	
}
