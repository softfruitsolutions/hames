package hames.service;

import hames.bean.Order;
import hames.core.service.AbstractService;

public interface OrderService extends AbstractService{
	
	/**
	 * Update Order
	 * @param order
	 */
	public void updateOrder(Order order);
	
	/**
	 * Process Order
	 * @param order
	 */
	public void processOrder(Order order);
	

}
