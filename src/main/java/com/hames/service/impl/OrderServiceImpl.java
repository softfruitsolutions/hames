package com.hames.service.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Validator;

import com.hames.bean.Customer;
import com.hames.bean.Order;
import com.hames.dao.CustomerDao;
import com.hames.dao.OrderDao;
import com.hames.exception.ValidationException;
import com.hames.service.GenericService;
import com.hames.service.OrderService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

@Repository
public class OrderServiceImpl extends GenericService implements OrderService{

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		return null;
	}

	@Override
	public void saveOrder(Order order) {
		/**
		 * Validating Order
		 */
		try{
			validate(order);
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
			throw new ValidationException();
		}
		
		/**
		 * Setting Audit details
		 * Save Order
		 */
		if(order.getOrderId() == null || order.getOrderId().isEmpty()){
			order.setDateCreated(new DateTime());
			order.setDateModified(new DateTime());	
		}else{
			order.setDateModified(new DateTime());
		}
		
		logger.debug("Saving entity : {},{}",order.getClass(),order.toString());
		orderDao.save(order);
		logger.debug("{} entity saved successfully",order.getOrderType().getValue());
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return orderDao.buildDatatable(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getOrderById(String orderId) {
		Order order = orderDao.findByOrderId(orderId);
		/* 
		 * Setting Party to Object
		 * TODO
		 */
		Customer customer = customerDao.findByCustomerId(order.getPartyId());
		order.setParty(customer);
		
		return (T) order;
	}

}
