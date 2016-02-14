package com.hames.dao;

import java.util.List;

import com.hames.bean.Customer;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface CustomerDao{ 

	/**
	 * Save Customer
	 * @param customer
	 */
	void save(Customer customer);
	
	/**
	 * Find a Customer
	 * @param customerId
	 * @return
	 */
	Customer findByCustomerId(String customerId);
	
	/**
	 * Get Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);
	
	/**
	 * Find All Customers
	 * @return
	 */
	List<Customer> findAllCustomers();
	
	/**
	 * Get Customer Count
	 * @return
	 */
	Long findCustomerCount();
	
}
