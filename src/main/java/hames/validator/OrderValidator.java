package hames.validator;

import hames.bean.Order;
import hames.enums.OrderStatusEnum;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;

		if(order.getJobName() == null || order.getJobName().isEmpty()){
			errors.rejectValue("jobName", "","Job Name Required");
		}
		
		if(order.getCustomerId()==null || order.getCustomerId() < -1){
			errors.rejectValue("customerId", "", "Customer Required");
		}
		
		if(order.getOrderDate() == null){
			errors.rejectValue("orderDate", "","Order Date Required");
		}
		
		if(order.getDeliveryDate() == null){
			errors.rejectValue("deliveryDate", "","Delivery Date Required");
			return;
		}
		
		if(order.getDeliveryDate().isBefore(order.getOrderDate())){
			errors.rejectValue("deliveryDate", "","Delivery date must be after Order Date");
		}
		
		if(order.getOrderStatus() == null || OrderStatusEnum.findEnum(order.getOrderStatus()) == null){
			errors.rejectValue("orderStatus", "", "Invalid Order status");
		}
		
		if(order.getTotalAmount() == null){
			errors.rejectValue("totalAmount", "", "Total Amount Required");
		}
		
	}

}
