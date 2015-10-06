package hames.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import hames.bean.Order;
import hames.core.service.AbstractServiceImpl;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService{

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		return Order.class;
	}

}
