package hames.validator;

import hames.bean.Order;
import hames.enums.OrderStatusEnum;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobName", "","Job Name Required");
		
		if(order.getCustomerId() < -1){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerId", "","Customer Required");
		}
		
		if(order.getOrderDate() == null || order.getOrderDate().isBeforeNow()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDate", "","Invalid Order creation date");
		}
		
		if(order.getDeliveryDate() == null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryDate", "","Delivery Date Required");
		}
		
		if(order.getOrderStatus() == null || OrderStatusEnum.findEnum(order.getOrderStatus()) == null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderStatus", "", "Invalid Order status");
		}
		
	}

}
