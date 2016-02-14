package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.Customer;
import com.hames.dao.CustomerDao;
import com.hames.exception.ValidationException;
import com.hames.service.CustomerService;
import com.hames.service.GenericService;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.validator.CustomerValidator;

@Service
public class CustomerServiceImpl extends GenericService implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Validator getValidator() {
		return new CustomerValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return Customer.class;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		/**
		 * validating Staff
		 */
		try{
			validate(customer);
		}catch(ValidationException e){
			throw new ValidationException(e.getMessage());
		}
		
		// Setting Audit details
		customer.setAuditableDetails(customer.getPartyId());
		
		logger.debug("Saving entity : {},{}",getEntityClass(),customer.toString());
		customerDao.save(customer);
		logger.debug("Entity saved successfully");
		
	}

	@Override
	public Customer getCustomerById(String customerId) {
		return customerDao.findByCustomerId(customerId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return customerDao.buildDatatable(request);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAllCustomers();
	}

	@Override
	public Long getCustomerCount() {
		return customerDao.findCustomerCount();
	}

}
