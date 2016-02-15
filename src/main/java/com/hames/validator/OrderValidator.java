package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.Order;
import com.hames.enums.OrderType;

public class OrderValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Order order = (Order) target;
		
		if(order.getStaffConcerned() == null || order.getStaffConcerned().isEmpty()){
			errors.rejectValue("staffConcerned", "","Staff concerned required");
		}
		
		if(order.getPartyId() == null || order.getPartyId().isEmpty()){
			errors.rejectValue("partyId", "", "Party required");
		}
		
		if(order.getOrderDate() == null){
			errors.rejectValue("orderDate", "","Order date required");
		}
		
		if(order.getDeliveryDate() == null){
			errors.rejectValue("deliveryDate", "","Delivery date required");
			return;
		}
		
		if(order.getDeliveryDate().isBefore(order.getOrderDate())){
			errors.rejectValue("deliveryDate", "","Delivery date must be after Order Date");
		}
		
		if(order.getOrderType() == null || !OrderType.isValidEnum(order.getOrderType())){
			errors.rejectValue("orderType","","Order Type Required");
		}
	}

}
