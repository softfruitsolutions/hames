package com.hames.service;

import java.util.List;

import com.hames.bean.Customer;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

public interface CustomerService{

	/**
	 * Save Customer
	 * @param customer
	 */
	public void saveCustomer(Customer customer);
	
	/**
	 * Get A Customer
	 * @param customerId
	 * @return
	 */
	public Customer getCustomerById(String customerId);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	/**
	 * Get All Customers 
	 * @return
	 */
	public List<Customer> getAllCustomers(); 
}
