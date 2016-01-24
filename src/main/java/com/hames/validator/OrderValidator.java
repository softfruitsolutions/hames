package com.hames.validator;

import org.apache.commons.lang3.EnumUtils;
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
		
		if(order.getPartyId() == null || order.getPartyId().isEmpty()){
			errors.rejectValue("partyId", "", "Party Required");
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
		
		if(order.getOrderType() == null || EnumUtils.isValidEnum(OrderType.class, order.getOrderType().getValue())){
			errors.rejectValue("orderType","","Order Type Required");
		}
	}

}
