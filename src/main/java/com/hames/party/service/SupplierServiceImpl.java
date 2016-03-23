package com.hames.party.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.party.model.Supplier;
import com.hames.party.validator.SupplierValidator;

@Service
public class SupplierServiceImpl extends PartyServiceImpl<Supplier> implements SupplierService{

	@Override
	public Validator getValidator() {
		return new SupplierValidator();
	}
	
	@Override
	public String save(Supplier t) {
		validate(t);
		
		//Setting Auditing details
		t.setAudit(t.getPartyId());
		
		return "";//super.save(t);
	}

}
