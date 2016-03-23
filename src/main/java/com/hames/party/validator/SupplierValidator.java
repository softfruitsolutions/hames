package com.hames.party.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.party.model.Supplier;

public class SupplierValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Supplier.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Supplier supplier = (Supplier) target;
		
		if(supplier.getName() == null || supplier.getName().isEmpty()){
			errors.rejectValue("name", "", "Supplier name required");
		}
		
		if(supplier.getType() == null){
			errors.rejectValue("type", "", "Supplier type required");
		}
		
		if(supplier.getStatus() == null){
			errors.rejectValue("status", "", "Supplier status required");
		}
		
		if(supplier.getContactNo() == null || supplier.getContactNo().isEmpty()){
			errors.rejectValue("contactNo", "", "Contact information required");
		}
		
		if(supplier.getPartyType() == null){
			errors.rejectValue("partyType", "", "Party type required");
		}
	}

}
