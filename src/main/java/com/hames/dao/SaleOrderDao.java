package com.hames.dao;

public interface SaleOrderDao extends OrderDao{

	/**
	 * Is Sale Order Job No Exists
	 * @param jobNo
	 * @return
	 */
	public boolean isJobNoExists(String jobNo);
}
