package com.hames.party.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.party.model.SupplierType;
import com.hames.party.validator.SupplierTypeValidator;
import com.hames.service.GenericServiceImpl;

@Service
public class SupplierTypeServiceImpl extends GenericServiceImpl<SupplierType> implements SupplierTypeService{

	@Override
	public Validator getValidator() {
		return new SupplierTypeValidator();
	}

	@Override
	public String save(SupplierType t) {
		
		validate(t);
		
		//Setting auditable details
		t.setAudit();
		
		return super.save(t);
	}
}
