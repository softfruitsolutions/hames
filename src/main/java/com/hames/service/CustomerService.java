package com.hames.service;

import java.util.List;

import com.hames.bean.Customer;

public interface CustomerService extends GenericService<Customer>{

	/**
	 * Get Active Customers
	 * @return
	 */
	public List<Customer> getActiveCustomers();
	
	/**
	 * Get Customer Count
	 * @return
	 */
	public Long getCustomerCount();
}
