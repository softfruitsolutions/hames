package com.hames.dao;

import com.hames.bean.Order;

public interface OrderDao {

	/**
	 * Save order
	 * @param order
	 */
	void save(Order order);
}
