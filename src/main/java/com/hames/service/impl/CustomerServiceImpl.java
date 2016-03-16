package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.Customer;
import com.hames.dao.CustomerDao;
import com.hames.service.CustomerService;
import com.hames.service.GenericServiceImpl;
import com.hames.validator.CustomerValidator;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Validator getValidator() {
		return new CustomerValidator();
	}


	@Override
	public String save(Customer customer) {
		
		// Setting Audit details
		customer.setAuditableDetails(customer.getPartyId());
		return super.save(customer);
	}

	@Override
	public Long getCustomerCount() {
		return customerDao.findCustomerCount();
	}

	@Override
	public List<Customer> getActiveCustomers() {
		return customerDao.findActiveCustomers();
	}

}
