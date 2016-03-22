package com.hames.dao;

import java.util.List;

import com.hames.mongo.GenericDao;
import com.hames.party.model.Customer;

public interface CustomerDao extends GenericDao<Customer>{ 

	/**
	 * Find Active Customers
	 * @return
	 */
	public List<Customer> findActiveCustomers();
	
	/**
	 * Get Customer Count
	 * @return
	 */
	Long findCustomerCount();
	
}
