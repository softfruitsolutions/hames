package hames.service;

import hames.bean.Order;
import hames.core.service.AbstractServiceImpl;
import hames.validator.OrderValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


	@Override
	public Validator getValidator() {
		return new OrderValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return Order.class;
	}



}
