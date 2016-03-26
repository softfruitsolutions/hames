package com.hames.party.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.exception.ValidationException;
import com.hames.party.model.Supplier;
import com.hames.party.validator.SupplierValidator;

@Service
public class SupplierServiceImpl extends PartyServiceImpl<Supplier> implements SupplierService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierServiceImpl.class);

	@Autowired
	private SupplierTypeService supplierTypeService;
	
	@Override
	public Validator getValidator() {
		return new SupplierValidator();
	}
	
	@Override
	public String save(Supplier t) {
		validate(t);
		
		if(!supplierTypeService.isExists(t.getType())){
			LOGGER.error("Invalid supplier type provided. Opeartion aborted.!");
			throw new ValidationException("Supplier type not found.");
		}
		
		//Setting Auditing details
		t.setAudit(t.getPartyId());
		
		return super.save(t);
	}

}
