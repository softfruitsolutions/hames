package com.hames.party.validator;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.party.enums.SupplierTypeStatus;
import com.hames.party.model.SupplierType;

public class SupplierTypeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SupplierType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SupplierType type = new SupplierType();
		
		if(type.getTypeName() == null || type.getTypeName().isEmpty()){
			errors.rejectValue("typeName", "", "Type name required");
		}
		
		if(type.getTypeStatus() == null){
			errors.rejectValue("typeStatus", "", "Type Status required");
			return;
		}
		
		if(!EnumUtils.isValidEnum(SupplierTypeStatus.class, type.getTypeStatus().name())){
			errors.rejectValue("typeStatus", "", "Type Status required");
		}
	}

}
