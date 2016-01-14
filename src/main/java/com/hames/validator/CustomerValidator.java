package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.Customer;

public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer) obj;
		if(customer.getFirstName() == null || customer.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "", "First Name Required");
		}
		
		if(customer.getLastName() == null || customer.getLastName().isEmpty()){
			errors.rejectValue("lastName", "", "Last Name Required");
		}

		if(customer.getStatus() == null){
			errors.rejectValue("status", "","Customer Status Required");
		}
		
		if(customer.getPartyType() == null){
			errors.rejectValue("partyType", "","Invalid Party Type ");
		}
	}

}
