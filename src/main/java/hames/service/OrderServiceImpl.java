package hames.service;

import hames.bean.Order;
import hames.bean.exception.OrderException;
import hames.core.service.AbstractServiceImpl;
import hames.enums.OrderStatusEnum;
import hames.validator.OrderValidator;

import org.joda.time.DateTime;
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

	@Override
	public void processOrder(Order order) {
		if(order.getOrderStatus() == OrderStatusEnum.DRAFT.getValue()){
			/**
			 * Order Creation
			 */
			order.setOrderStatus(OrderStatusEnum.CREATED.getValue());
			
			/**
			 * Setting Auditable details
			 */
			order.setDateCreated(new DateTime());
			order.setDateModified(new DateTime());
			save(order);
		}else{
			logger.debug("Cannot process an order with status {}. Operation Aborted.!",order.getOrderStatus());
			throw new OrderException("Order can't be processed");
		}
		
	}

	@Override
	public void updateOrder(Order order) {
		
		
	}

	

}
