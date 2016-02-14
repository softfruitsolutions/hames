package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.SaleOrder;
import com.hames.enums.SaleOrderStatus;

public class SaleOrderValidator extends OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SaleOrder.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

		super.validate(target, errors);
		
		SaleOrder saleOrder = (SaleOrder) target;
		
		if(saleOrder.getJobNo() == null || saleOrder.getJobNo().isEmpty()){
			errors.rejectValue("jobNo", "","Job No Required");
		}
		
		if(saleOrder.getJobName() == null || saleOrder.getJobName().isEmpty()){
			errors.rejectValue("jobName", "","Job Name Required");
		}
		
		if(!SaleOrderStatus.isValidEnum(saleOrder.getSaleOrderStatus())){
			errors.rejectValue("saleOrderStatus","","Invalid Sale order status");
		}
	}
}
