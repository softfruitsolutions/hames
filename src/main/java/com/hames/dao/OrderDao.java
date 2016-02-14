package com.hames.dao;

import com.hames.bean.Order;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface OrderDao {

	/**
	 * Save order
	 * @param order
	 */
	void save(Order order);
	
	/**
	 * Get Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);

	/**
	 * Order Exists
	 * @param OrderId
	 * @return
	 */
	boolean orderExists(String orderId);
	
	/**
	 * Find Order By ID
	 * @param orderId
	 * @return
	 */
	<T> T findByOrderId(String orderId);
}
